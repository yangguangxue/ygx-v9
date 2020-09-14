package com.ygx.ygxv9productservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ygx.api.product.IProductService;
import com.ygx.api.product.vo.ProductVO;
import com.ygx.entity.TProduct;
import com.ygx.entity.TProductDesc;
import com.ygx.mapper.TProductDescMapper;
import com.ygx.mapper.TProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Service
public class ProductServiceImpl  implements IProductService {

    @Autowired
    private TProductMapper productMapper;

    @Autowired
    private TProductDescMapper productDescMapper;



    @Override
    public PageInfo<TProduct> page(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<TProduct> list = productMapper.list();
        PageInfo<TProduct> pageInfo = new PageInfo<>(list, 3);
        return pageInfo;
    }

    @Override
    public Long addProduct(ProductVO productVO) {
        //添加商品的基本信息
        TProduct product = productVO.getProduct();
        product.setFlag(true);
        product.setCreateUser(1L);
        product.setUpdateUser(1L);
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        //设置主键回填
        productMapper.insertSelective(product);

        //添加商品的描述信息
        TProductDesc productDesc = new TProductDesc();
        productDesc.setProductId(product.getId());
        productDesc.setpDesc(productVO.getProductDesc());
        productDescMapper.insertSelective(productDesc);
        return product.getId();
    }

    @Override
    public List<TProduct> list() {
        return productMapper.list();
    }
}
