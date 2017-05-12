package cn.effine.lab.solr;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.params.SolrParams;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author effine  Email: zhangyafei#co-mall.com
 * @version 3.2.1
 * @company 北京科码先锋软件技术有限公司@版权所有
 * @since 2017-05-10 14:51
 */
public class SolrJ {


    private static String url = "http://localhost:8010/solr";

    public static void main(String[] args) throws IOException, SolrServerException {

        SolrServer solrServer = new HttpSolrServer(url);


        SolrParams solrParams = new SolrParams() {
            @Override
            public String get(String param) {
                return null;
            }

            @Override
            public String[] getParams(String param) {
                return new String[0];
            }

            @Override
            public Iterator<String> getParameterNamesIterator() {
                return null;
            }
        }

        // 清空索引
        //solrServer.deleteByQuery("*:*");


    }
}
