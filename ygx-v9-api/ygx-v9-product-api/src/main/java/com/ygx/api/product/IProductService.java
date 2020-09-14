package com.ygx.api.product;

import com.github.pagehelper.PageInfo;
import com.ygx.api.product.vo.ProductVO;
import com.ygx.entity.TProduct;
import com.ygx.v9.common.IBaseService;

import java.util.List;

public interface IProductService  {
    PageInfo<TProduct> page(Integer pageIndex, Integer pageSize);

    Long addProduct(ProductVO productVO);

    List<TProduct> list();
}
