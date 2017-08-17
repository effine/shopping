/**
 * @author effine
 * @email iballader#gmail.com
 * @date Jul 6, 2016  10:38:52 PM
 * @site http://effine.cn
 * @since 1.0
 */
package cn.effine.lab.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.testng.Assert;

public class HttpRequestUtilsTest {

    @Test
    public void get() throws Exception {

        String url = "http://pic1.womai.com/upload/601/603/606/6600/245320/613770/613770_1_pic500_53711.jpg";


        //1.获得一个httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //2.生成一个get请求
        HttpGet httpget = new HttpGet(url);
        //3.执行get请求并返回结果
        CloseableHttpResponse response = httpclient.execute(httpget);
        try {
            //4.处理结果
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);

        } finally {
            response.close();
        }


        HttpGet httpGet = new HttpGet(url);
        httpGet.getRequestLine();
    }
}