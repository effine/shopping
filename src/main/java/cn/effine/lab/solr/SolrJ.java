package cn.effine.lab.solr;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;

/**
 * @author effine  Email: zhangyafei#co-mall.com
 * @version 3.2.1
 * @company 北京科码先锋软件技术有限公司@版权所有
 * @since 2017-05-10 14:51
 */
public class SolrJ {


    // 如果不指定Core的名称product，则默认为collection1
    //  TODO 验证上面的注释内容
    private static String baseURL = "http://localhost:8010/solr/product";

    public static void main(String[] args) throws IOException, SolrServerException {

        SolrServer solrServer = new HttpSolrServer(baseURL);

        // 插入数据: 原生方式
        System.out.println("向solr插入数据...");
        SolrInputDocument solrInputDocument = new SolrInputDocument();
        solrInputDocument.setField("id", 1);
        solrInputDocument.setField("name", "张亚飞");
        solrInputDocument.setField("nameEN", "effine");
        solrInputDocument.setField("description", "描述");
        solrInputDocument.setField("descriptionEN", "description");
        solrServer.add(solrInputDocument);
        solrServer.commit();
        System.out.println("向solr插入数据完成");


        // 插入数据：Bean方式
        ProductPO po = new ProductPO();
        po.setId(2);
        po.setName("刘川");
        po.setNameEN("chuan");
        po.setDescription("描述");
        po.setDescriptionEN("desc");

        solrServer.addBean(po);
        solrServer.commit();
        System.out.println("Bean方式插入数据完成");



        // 清空索引
        //solrServer.deleteByQuery("*:*");


    }
}