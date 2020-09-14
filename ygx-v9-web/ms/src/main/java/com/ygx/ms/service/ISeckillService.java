package com.ygx.ms.service;

public interface ISeckillService {

    public void seckill(Long seckillId ,Long userId);

    String sendMessageToOrder(Long seckillId, Long userId);
}
