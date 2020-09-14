package com.ygx.ygxv9background.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.ygx.api.product.IProductTypeService;
import com.ygx.entity.TProductType;
import com.ygx.ygxv9background.Filter.CrosFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("productType")
public class ProductTypeController {

    @Reference
    private IProductTypeService productTypeService;

    @RequestMapping("/list")
    public List<TProductType> list(){
        return productTypeService.list();
    }

    @RequestMapping("/listForJsonp")
    public String listForJsonp(String callback){
        List<TProductType> list = productTypeService.list();
        String gson = new Gson().toJson(list);
        return callback + "("+gson+")";
    }



}
