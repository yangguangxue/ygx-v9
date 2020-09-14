package com.ygx.ygxitemservice;

import com.ygx.item.ItemService;
import com.ygx.ygxitemservice.service.ItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class YgxItemServiceApplicationTests {

    @Autowired
    private ItemService itemService;

    @Test
    void contextLoads() {
        itemService.createHtmlById(3L);
}

}
