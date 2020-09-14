package com.ygx.ygxv9productservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.ygx.api.product.IProductTypeService;
import com.ygx.entity.TProductType;
import com.ygx.mapper.TProductTypeMapper;
import com.ygx.v9.common.BaseServiceImpl;
import com.ygx.v9.common.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service
public class ProductTypeService extends BaseServiceImpl<TProductType> implements IProductTypeService {

    @Autowired
    private TProductTypeMapper productTypeMapper;

    @Override
    public IBaseDao<TProductType> getBaseDao() {
        return productTypeMapper;
    }
}

