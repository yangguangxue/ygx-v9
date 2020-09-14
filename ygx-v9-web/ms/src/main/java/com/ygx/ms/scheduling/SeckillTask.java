package com.ygx.ms.scheduling;

import com.ygx.ms.constant.MiaoshaConstant;
import com.ygx.ms.dao.TSeckillMapper;
import com.ygx.ms.entity.TSeckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeckillTask {
    // 自动扫描秒杀活动表，根据开始时间开启/关闭秒杀活动

    @Autowired
    private TSeckillMapper seckillMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Scheduled(cron = "0/10 * * * * ?")
    public void startSeckills(){
        //1 查看可以开启的秒杀活动
        List<TSeckill> list = seckillMapper.getCanStartSeckills();
        //2 更新状态 0-1
        for (TSeckill tSeckill : list) {
            //秒杀活动已开启
            tSeckill.setStatus("1");
            seckillMapper.updateSeckillStatus(tSeckill);
            //3 redis 创建list 用于保存本次秒杀活动包含的商品信息
            //list
            for (Integer i = 0; i < tSeckill.getCount(); i++) {
                String key = new StringBuilder(MiaoshaConstant.SECKILL_COUNT_PRE).append(tSeckill.getId()).toString();
                redisTemplate.opsForList().leftPush(key, tSeckill.getProductId());
            }
            // TODO 保存当前的信息活动到redis中
            String seckillKey = new StringBuilder(MiaoshaConstant.SECKILL_PRE).append(tSeckill.getId()).toString();
            redisTemplate.opsForValue().set(seckillKey,tSeckill);
        }

    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void stopSeckills(){
        List<TSeckill> list = seckillMapper.getCanStopSeckills();
        // 更新状态
        for (TSeckill seckill : list) {
            seckill.setStatus("2");
            seckillMapper.updateSeckillStatus(seckill);
            // redis 删除List
            String key = new StringBuilder(MiaoshaConstant.SECKILL_COUNT_PRE).append(seckill.getId()).toString();
            redisTemplate.delete(key);

            String seckillKey = new StringBuilder(MiaoshaConstant.SECKILL_PRE).append(seckill.getId()).toString();
            redisTemplate.delete(seckillKey);
        }
    }
}
