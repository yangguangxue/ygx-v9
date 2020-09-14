package com.ygx.ygxv9search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ygx.api.search.ISearchService;
import com.ygx.entity.TProduct;
import com.ygx.v9.pojo.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("search")
public class SearchController {

    @Reference
    private ISearchService searchService;

    @RequestMapping("initAllData")
    @ResponseBody
    public ResultBean initAllData(){

        return searchService.initAllData();
    }

    @RequestMapping("searchByKeyWord")
    public String searchByKeyWord(String keyWord, Model model){
        List<TProduct> list = searchService.searchByKeyWord(keyWord);
        model.addAttribute("list",list);
        return "search";
    }

}
