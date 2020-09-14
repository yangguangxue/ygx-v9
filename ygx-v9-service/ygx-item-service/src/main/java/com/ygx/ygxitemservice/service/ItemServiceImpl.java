package com.ygx.ygxitemservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.ygx.entity.TProduct;
import com.ygx.item.ItemService;
import com.ygx.mapper.TProductMapper;
import com.ygx.v9.pojo.ResultBean;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Component
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TProductMapper productMapper;

    @Autowired
    private Configuration configuration;

    //JDKti提供的线程池
     private ExecutorService pool = Executors.newSingleThreadExecutor();
    private ExecutorService pool2 = Executors.newFixedThreadPool(10);
    private ExecutorService pool3 = Executors.newCachedThreadPool();

    //结合真实服务器硬件条件来设置
    private int corePoolSize = Runtime.getRuntime().availableProcessors();

    //创建自己的线程
    private ExecutorService pool4 = new ThreadPoolExecutor(4,corePoolSize*2,0L, TimeUnit.SECONDS,new LinkedBlockingDeque<>(100));

    @Value("${html.location}")
    private String htmlLocation;

  @Override
    public ResultBean createHtmlById(Long productId) {
        //根据id获取数据
      TProduct product = productMapper.selectByPrimaryKey(productId);
      try {
          Template template = configuration.getTemplate("item.ftl");
          Map<String,Object> map = new HashMap<>();
          map.put("product",product);
          FileWriter fileWriter = new FileWriter(htmlLocation + productId + "html");
          template.process(map,fileWriter);
      } catch (IOException | TemplateException e) {
          e.printStackTrace();
          return ResultBean.error("生成静态页面失败");
      }

      return ResultBean.error("生成静态页面成功");
    }

    @Override
    public ResultBean batchCreateHtml(List<Long> idList) {
        List<Future> list = new ArrayList<>(idList.size());
        for (Long id : idList) {
            //提交一个人物给线程池执行
            Future<Boolean> submit = pool4.submit(new CreateHtml(id));
        }
        //查看执行结果
        for (Future future : list) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return ResultBean.success("批量生成页面成功");
    }

    private class CreateHtml implements Callable<Boolean> {

        private Long productId;

        public CreateHtml(Long productId){
            this.productId = productId;
        }

        @Override
        public Boolean call() throws Exception {
            //生成静态页面
            //根据id获取数据
            TProduct product = productMapper.selectByPrimaryKey(productId);
            try {
                Template template = configuration.getTemplate("item.ftl");
                Map<String, Object> map = new HashMap<>();
                map.put("product", product);
                FileWriter fileWriter = new FileWriter(htmlLocation + productId + "html");
                template.process(map, fileWriter);
            } catch (IOException | TemplateException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }
    }
}
