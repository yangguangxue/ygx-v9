package com.ygx.ms.dao;

import com.ygx.ms.entity.TSeckill;

import java.util.List;

public interface TSeckillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TSeckill record);

    int insertSelective(TSeckill record);

    TSeckill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TSeckill record);

    int updateByPrimaryKey(TSeckill record);

    List<TSeckill> getCanStartSeckills();

    void updateSeckillStatus(TSeckill tSeckill);

    List<TSeckill> getCanStopSeckills();
}