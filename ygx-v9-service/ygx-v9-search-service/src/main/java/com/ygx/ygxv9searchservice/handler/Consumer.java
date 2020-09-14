package com.ygx.ygxv9searchservice.handler;

import com.ygx.api.search.ISearchService;
import com.ygx.v9.constant.RabbitMQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private ISearchService searchService;

    @RabbitListener(queues = RabbitMQConstant.BACKGORUND_PRODUCT_SAVE_UPDATE_QUEUE)
    public void processAddOrUpdate(Long productId){
        searchService.updateById(productId);
    }
}
