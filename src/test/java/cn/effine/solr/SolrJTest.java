package cn.effine.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.testng.annotations.Test;

import java.util.Collection;

/**
 * @author effine  Email: zhangyafei#co-mall.com
 * @version 3.2.1
 * @company 北京科码先锋软件技术有限公司@版权所有
 * @since 2017-05-16 17:19
 */
public class SolrJTest {

    private static String baseURL = "http://localhost:8010/solr/\n";

    @Test(description = "solrj索引查询")
    public void queryTest() throws SolrServerException {

        SolrServer server = new HttpSolrServer(baseURL);

        SolrQuery query = new SolrQuery();
        query.set("q", "+name:effine");

        QueryResponse response = server.query(query);

        SolrDocumentList list = response.getResults();

        System.out.println("返回结果数：" + list.getNumFound());
        for (SolrDocument solrDocument : list) {
            Collection<String> fieldNames = solrDocument.getFieldNames();
            for (String fieldname : fieldNames) {
                System.out.println(fieldname + " = " + solrDocument.getFieldValue(fieldname));
            }
        }
    }
}
