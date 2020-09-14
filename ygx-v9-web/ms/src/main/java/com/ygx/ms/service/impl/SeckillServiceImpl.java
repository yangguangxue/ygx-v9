package com.ygx.ms.service.impl;

import com.ygx.ms.constant.MiaoshaConstant;
import com.ygx.ms.entity.TSeckill;
import com.ygx.ms.exception.SeckillException;
import com.ygx.ms.service.ISeckillService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class SeckillServiceImpl implements ISeckillService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void seckill(Long seckillId, Long userId) {

        //当前用户是否抢购到商品
        String luckyKey = new StringBuilder(MiaoshaConstant.SECKILL_USER_PRE).append(seckillId).toString();
        //判断当前用户是否已抢购过
        Boolean isExists = redisTemplate.opsForSet().isMember(luckyKey, userId);
        if (isExists) {
            throw new SeckillException("您已经抢购过了");
        }

        // 确定活动是否已经结束
        String seckillKey = new StringBuilder(MiaoshaConstant.SECKILL_PRE).append(seckillId).toString();
        TSeckill seckill = (TSeckill) redisTemplate.opsForValue().get(seckillKey);
        if ("2".equals(seckill.getStatus())) {
            throw new SeckillException("当前秒杀活动已结束！");
        }
        // 活动是否开启
        if ("0".equals(seckill.getStatus())) {
            throw new SeckillException("当前活动还未开启！");
        }
        //活动开启
        //获取秒杀活动队列
        String key = new StringBuilder(MiaoshaConstant.SECKILL_COUNT_PRE).append(seckillId).toString();
        //弹出对象
        //优化
        //   优先判断对象是否已抢购
        //   将数据库查询操作变成redis查询
        Long productId = (Long) redisTemplate.opsForList().leftPop(key);
        if (productId != null) {
            //将本次抢购到商品的用户id 保存到set中
            redisTemplate.opsForSet().add(luckyKey,userId);
        }else {
            throw new SeckillException("当前商品已被抢完");
        }
    }

    @Override
    public String sendMessageToOrder(Long seckillId, Long userId) {
        //1. 构建业务数据
        Map<String,Object> map = new HashMap<>();
        // 根据秒杀活动id 获取到秒杀信息
        String seckillKey = new StringBuilder(MiaoshaConstant.SECKILL_PRE).append(seckillId).toString();
        TSeckill seckill = (TSeckill) redisTemplate.opsForValue().get(seckillKey);        // 生成订单号  时间戳+用户编号
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderNo = new StringBuilder(format.format(date)).append("X").append(userId).toString();
        //订单编号 商品编号  秒杀价格  用户id
        //商品编号  商品的信息
        // 生成订单信息
        map.put("orderNo",orderNo);
        map.put("userId",userId);
        map.put("productId",seckill.getProductId());
        map.put("salePrice",seckill.getSalePrice());


        // 2 发送消息
        rabbitTemplate.convertAndSend("order-exchange","",map);

        //发送消息给前台
        return orderNo;
    }
}
