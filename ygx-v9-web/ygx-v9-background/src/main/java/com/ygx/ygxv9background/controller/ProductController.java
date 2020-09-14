package com.ygx.ygxv9background.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.ygx.api.product.IProductService;
import com.ygx.api.product.vo.ProductVO;
import com.ygx.api.search.ISearchService;
import com.ygx.entity.TProduct;
import com.ygx.item.ItemService;
import com.ygx.v9.constant.RabbitMQConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    @Reference
    private IProductService productService;

    @Reference
    private ISearchService searchService;

    @Reference
    private ItemService itemService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("list")
    public String list(Model model){
        List<TProduct> list = productService.list();
        model.addAttribute("list",list);
        return "product/list";
    }

    @RequestMapping("page/{pageIndex}/{pageSize}")
    public String page(Model model, @PathVariable("pageIndex") Integer pageIndex,
                       @PathVariable("pageSize") Integer pageSize){
//        List<TProduct> list = productService.list();
//        model.addAttribute("list",list);
        PageInfo<TProduct> pageInfo = productService.page(pageIndex,pageSize);
        model.addAttribute("pageInfo",pageInfo);
        return "product/list";
    }

    @PostMapping("add")
    public String addProduct(ProductVO productVO){
        Long productId = productService.addProduct(productVO);
        //发送消息到交换机
        rabbitTemplate.convertAndSend(RabbitMQConstant.BACKGROUND_EXCHANGE,"product.add",productId);
//        searchService.updateById(productId);
//        itemService.createHtmlById(productId);
        return "redirect:/product/page/1/1";
    }


}
