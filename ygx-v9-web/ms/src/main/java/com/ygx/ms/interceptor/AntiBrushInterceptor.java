package com.ygx.ms.interceptor;

import com.ygx.ms.constant.MiaoshaConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class AntiBrushInterceptor implements HandlerInterceptor {


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        //设置编码
        response.setContentType("text/html;charset=utf-8");
        //1.获取客户端ip地址
        String remoteAddr = request.getRemoteAddr();
        //查看是否在黑名单内
        Boolean member = redisTemplate.opsForSet().isMember(MiaoshaConstant.USER_VIEW_BLACK, remoteAddr);
        if (member) {
            return reponseForBlack(remoteAddr,response);
        }
        //2. 将访问过的ip存入redis中，并对其访问次数进行统计
        //对value值需要做一些递进操作，需设置value的序列化方式为String
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
        //3.根据用户ip 查看其访问频率
        String key = new StringBuilder(MiaoshaConstant.USER_VIEW).append(remoteAddr).toString();
        Integer value = (Integer) redisTemplate.opsForValue().get(key);
        //第一次访问
        if (value==null) {
            redisTemplate.opsForValue().set(key,1);
            //设置有效期为一分钟
            redisTemplate.expire(key,1, TimeUnit.MINUTES);
            //放行  可以访问
            return true;
        }
        //增加访问的次数
        //redisTemplate.opsForValue().increment(key,1L);

        value =value + 1 ;
        redisTemplate.opsForValue().set(key,value);
        //做次数的判断

        if (value >= 5 && value <=10) {
            //达到警告线
            String msg = "您的操作次数过于频繁，请稍后在试！";
            response.getWriter().write(msg);
            return false;
        }
        //由于浏览器和测压工具ip地址不一致  将次数调小
        //测试用
        if (value>10) {
            //将当前客户ip加入到黑名单
            //采用set 保证唯一性
            return reponseForBlack(remoteAddr,response);
        }
        return true;
    }

    private boolean reponseForBlack(String remoteAddr,HttpServletResponse response) throws IOException {
        redisTemplate.opsForSet().add(MiaoshaConstant.USER_VIEW_BLACK,remoteAddr);
        String msg = "您已被加入黑名单，请联系客服进行解绑";
        response.getWriter().write(msg);
        return false;
    }
}
