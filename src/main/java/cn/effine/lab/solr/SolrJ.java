package cn.effine.lab.solr;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

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


        // 清空索引
        //solrServer.deleteByQuery("*:*");


    }
}