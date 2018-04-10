package cn.effine.lab.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.Collection;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 */
public class SolrJ {

    /**
     * 如果不指定Core的名称product，则默认为collection1
     */
    private static final String BASE_URL = "http://localhost:8010/solr/product";
    private static SolrServer solrServer = null;

    static {
        solrServer = new HttpSolrServer(BASE_URL);
    }

    public static void main(String[] args) throws IOException, SolrServerException {

        createForOrigin();

        createForBean();

        query();
    }

    /**
     * 原生方式创建索引
     */
    public static void createForOrigin() throws IOException, SolrServerException {
        System.out.println("原生方式向solr创建索引...");
        SolrInputDocument solrInputDocument = new SolrInputDocument();
        solrInputDocument.setField("id", 1);
        solrInputDocument.setField("name", "张亚飞");
        solrInputDocument.setField("nameEN", "effine");
        solrInputDocument.setField("description", "描述");
        solrInputDocument.setField("descriptionEN", "description");

        solrServer.add(solrInputDocument);

        SolrInputDocument solrInputDocument1 = new SolrInputDocument();
        solrInputDocument1.setField("id", 3);
        solrInputDocument1.setField("name", "张亚飞1");
        solrInputDocument1.setField("nameEN", "effine");
        solrInputDocument1.setField("description", "描述");
        solrInputDocument1.setField("descriptionEN", "description");

        solrServer.add(solrInputDocument1);

        solrServer.commit();
        System.out.println("原生方式向solr创建索引完成");
    }

    /**
     * Bean方式创建索引
     */
    public static void createForBean() throws IOException, SolrServerException {
        System.out.println("Bean方式向solr创建索引...");
        ProductPO po = new ProductPO();
        po.setId(2);
        po.setName("刘川");
        po.setNameEN("chuan");
        po.setDescription("描述");
        po.setDescriptionEN("desc");

        solrServer.addBean(po);
        solrServer.commit();
        System.out.println("Bean方式创建索引据完成");
    }

    /**
     * 删除索引
     */
    public static void removeIndex() throws IOException, SolrServerException {
        solrServer.deleteByQuery("name:刘川");
        solrServer.commit();
    }

    /**
     * 查询所有索引
     *
     * @throws SolrServerException
     */
    public static void query() throws SolrServerException {

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("q", "*:*");
        QueryResponse queryResponse = solrServer.query(solrQuery);

        // 获取查询结果
        SolrDocumentList solrDocumentList = queryResponse.getResults();

        //  注：solrDocumentList 的长度为第一页的数据长度
        System.out.println("返回结果数：" + solrDocumentList.getNumFound());
        System.out.println("----------------------");
        for (SolrDocument solrDocument : solrDocumentList) {
            // 获取每个 SolrDocument 中的字段及值
            Collection<String> fieldNames = solrDocument.getFieldNames();
            for (String fieldname : fieldNames) {
                System.out.println(fieldname + " = " + solrDocument.getFieldValue(fieldname));
            }
            System.out.println("----------------------");
        }

    }
}