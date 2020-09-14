package com.ygx.ms.dao;

import com.ygx.ms.entity.TProduct;

public interface TProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TProduct record);

    int insertSelective(TProduct record);

    TProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TProduct record);

    int updateByPrimaryKey(TProduct record);

    Integer getStoreById(Long id);

    void updateStoreById(Long id);
}