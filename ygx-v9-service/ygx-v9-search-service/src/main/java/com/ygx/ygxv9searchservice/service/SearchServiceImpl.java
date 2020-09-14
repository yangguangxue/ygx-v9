package com.ygx.ygxv9searchservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.ygx.api.search.ISearchService;
import com.ygx.entity.TProduct;
import com.ygx.mapper.TProductMapper;
import com.ygx.v9.pojo.ResultBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class SearchServiceImpl implements ISearchService {

    @Autowired
    private TProductMapper productMapper;

    @Autowired
    private SolrClient solrClient;

    @Override
    public ResultBean initAllData() {
        //获取到数据库数据
        List<TProduct> list = productMapper.list();
        //转换成document,保存到solr中
        for (TProduct product : list) {
            SolrInputDocument document = new SolrInputDocument();
            document.setField("id",product.getId());
            document.setField("product_name",product.getName());
            document.setField("product_images",product.getImage());
            document.setField("product_price",product.getPrice());
            document.setField("product_sale_point",product.getSalePoint());
            try {
                solrClient.add(document);
            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                return ResultBean.error("添加到索引库失败");
            }
        }
        try {
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return ResultBean.error("添加到索引库失败");
        }
        return ResultBean.success("数据存储成功");
    }



    @Override
    public List<TProduct> searchByKeyWord(String keyWord) {
        //组装查询条件
        SolrQuery solrCondition = new SolrQuery();
        if (!StringUtils.isAllEmpty(keyWord)) {
            solrCondition.setQuery("product_keywords:"+keyWord);
        }else {
            solrCondition.setQuery("product_keywords:华为");

        }
        List<TProduct> results = null;
        try {
            //执行查询
            QueryResponse response = solrClient.query(solrCondition);
            SolrDocumentList list = response.getResults();
            results = new ArrayList<>(list.size());
            for (SolrDocument document : list) {
                TProduct product = new TProduct();
                product.setId(Long.parseLong(document.getFieldValue("id").toString()));
                product.setName(document.getFieldValue("product_name").toString());
                product.setImage(document.getFieldValue("product_images").toString());
                product.setPrice(Long.parseLong(document.get("product_price").toString()));
                product.setSalePoint(document.getFieldValue("product_sale_point").toString());
                results.add(product);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public ResultBean updateById(Long id) {
        //获取到数据库数据
        TProduct product = productMapper.selectByPrimaryKey(id);
        SolrInputDocument document = new SolrInputDocument();
        document.setField("id",product.getId());
        document.setField("product_name",product.getName());
        document.setField("product_images",product.getImage());
        document.setField("product_price",product.getPrice());
        document.setField("product_sale_point",product.getSalePoint());
        try {
            solrClient.add(document);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return ResultBean.error("添加到索引库失败");
        }

        try {
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return ResultBean.error("添加到索引库失败");
        }
        return ResultBean.success("数据存储成功");
    }

}
