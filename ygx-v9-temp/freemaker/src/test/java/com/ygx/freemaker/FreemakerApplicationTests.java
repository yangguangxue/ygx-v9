package com.ygx.freemaker;

import com.ygx.freemaker.entity.Product;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class FreemakerApplicationTests {

    @Autowired
    private Configuration configuration;

    @Test
    public void createHtml() throws IOException, TemplateException {
        //模板+数据=输出
        //模板
        Template template = configuration.getTemplate("freemarker.ftl");
        //数据
        Map<String,Object> data = new HashMap<>();
        data.put("name","hello freemarker!");
        //结合输出
        Product product = new Product();
        product.setId(1);
        product.setName("freemaker");
        product.setBirthday(new Date());
        data.put("product",product);
        FileWriter fileWriter = new FileWriter("E:\\ygx-v9\\ygx-v9-temp\\freemaker\\src\\main\\resources\\templates\\freemarker.html");
        template.process(data,fileWriter);
        System.out.println("生成静态页成功！");
    }
}
