package com.ygx.ms.service.impl;

import com.ygx.ms.dao.TProductMapper;
import com.ygx.ms.entity.TProduct;
import com.ygx.ms.service.IMiaoShaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class miaoShaServiceImpl  implements IMiaoShaService {

    @Autowired
    TProductMapper productMapper;

    @Override
    public TProduct getById(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean saleById(Long id) {
        Integer store = productMapper.getStoreById(id);
        if (store>0) {
            productMapper.updateStoreById(id);
            return true;
        }
        return false;
    }
}
