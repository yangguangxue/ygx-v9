package com.ygx.ygxv9index.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ygx.api.product.IProductTypeService;
import com.ygx.entity.TProductType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("index")
public class IndexController {

    @Reference
    private IProductTypeService productTypeService;

    @RequestMapping("show")
    public String showIndex(Model model){
        List<TProductType> list = productTypeService.list();
        model.addAttribute("list",list);
        return "index";
    }

    @RequestMapping("list")
    @ResponseBody
    public List<TProductType> list(){
        return productTypeService.list();
    }
}
