package cn.effine.index;

import cn.effine.model.Goods;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;

import java.io.IOException;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 * 索引加工类
 */
public class IndexProcess<T> {

    /**
     * TODO solr服务器地址添加到配置文件
     */
    private SolrServer server = new HttpSolrServer("http://localhost:8030/solr");

    /**
     * 删除所有
     *
     * @param id 删除指定ID所有
     * @return 是否删除成功
     */
    public boolean deleteIndex(long id) throws IOException, SolrServerException {
        UpdateResponse response = server.deleteById(String.valueOf(id));
        return response.getStatus() == 200;
    }

    /**
     * 创建索引
     *
     * @param t 创建索引对象
     * @return 是否创建成功
     */
    public boolean buildIndex(T t) throws IOException, SolrServerException {
        // 创建商品索引
        if (t instanceof Goods) {
            UpdateResponse response = server.addBean((Goods) t);
            return response.getStatus() == 200;
        }
        return false;
    }

}
