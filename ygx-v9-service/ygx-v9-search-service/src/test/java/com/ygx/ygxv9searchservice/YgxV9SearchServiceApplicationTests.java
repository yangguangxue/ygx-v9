package com.ygx.ygxv9searchservice;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class YgxV9SearchServiceApplicationTests {

    @Autowired
    private SolrClient solrClient;

    @Test
    void contextLoads() {
    }

    @Test
    public void addOrUpdateTest() throws IOException, SolrServerException {
        SolrInputDocument document = new SolrInputDocument();
        //需要一个唯一标识
        document.setField("id","102");
        document.setField("product_name","哈哈哈香港红馆演唱会门票");
        document.setField("product_price","19999");
        document.setField("product_sale_point","男神");
        document.setField("product_images","暂无");

        //提交
        solrClient.add(document);
        solrClient.commit();
    }

    @Test
    public void queryTest() throws IOException, SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
//        solrQuery.setQuery("*:*");
        solrQuery.setQuery("product_name:张学友");
        //
        QueryResponse query = solrClient.query(solrQuery);
        SolrDocumentList results = query.getResults();
        for (SolrDocument result : results) {
            System.out.println(result.get("id"));
            System.out.println(result.get("product_name"));
            System.out.println(result.get("product_price"));
        }
    }

    @Test
    public void deleteTest() throws IOException, SolrServerException {
        solrClient.deleteByQuery("product_name:张学友");
        solrClient.commit();
    }
}
