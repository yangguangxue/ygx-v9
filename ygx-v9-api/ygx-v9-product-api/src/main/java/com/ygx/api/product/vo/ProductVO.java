package com.ygx.api.product.vo;

import com.ygx.entity.TProduct;

import java.io.Serializable;

public class ProductVO implements Serializable {
    private TProduct product;
    private String productDesc;

    public TProduct getProduct() {
        return product;
    }

    public void setProduct(TProduct product) {
        this.product = product;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}
