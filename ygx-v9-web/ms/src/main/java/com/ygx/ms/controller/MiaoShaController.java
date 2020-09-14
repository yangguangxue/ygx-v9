package com.ygx.ms.controller;

import com.ygx.ms.entity.TProduct;
import com.ygx.ms.exception.SeckillException;
import com.ygx.ms.pojo.ResultBean;
import com.ygx.ms.service.IMiaoShaService;
import com.ygx.ms.service.ISeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("miaosha")
public class MiaoShaController {

    @Autowired
    IMiaoShaService iMiaoShaService;

    @Autowired
    ISeckillService seckillService;



    @RequestMapping("getById")
    @ResponseBody
    public TProduct getById(Long id){
        TProduct product = iMiaoShaService.getById(id);
        return product;
    }

    @RequestMapping("/htmlTest")
    public String htmlTest(){
        System.out.println("good --**********************************");
        return "hello";
    }

    @RequestMapping("saleById")
    @ResponseBody
    public String saleById(Long id){
        boolean result = iMiaoShaService.saleById(id);
        if (result) {
            return "success";
        }
        return "fail";
    }

    @RequestMapping("seckill")
    @ResponseBody
    public ResultBean seckill(Long seckillId, Long userId){
        try {
            seckillService.seckill(seckillId,userId);
            //
            String orderNo = seckillService.sendMessageToOrder(seckillId, userId);
            return new ResultBean("200",orderNo);
        } catch (SeckillException e) {
            return new ResultBean("200",e.getMessage());
        }
    }


}
