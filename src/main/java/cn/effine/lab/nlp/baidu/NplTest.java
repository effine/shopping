package cn.effine.lab.nlp.baidu;

import com.baidu.aip.nlp.AipNlp;
import org.json.JSONObject;

/**
 * @author effine
 * @Date 2017-08-31 01:07
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 * @sine 0.1
 */
public class NplTest {

    // 文档：https://cloud.baidu.com/doc/NLP/NLP-Java-SDK.html#.E5.BF.AB.E9.80.9F.E5.85.A5.E9.97.A8

    public static final String Access_Key_ID = "3ceb954e46ee485a9fab65348e881c4b";
    public static final String Secret_Access_ID = "8f17ed9cb39a450f83cfb0ad64820eb7";

    //设置APPID/AK/SK
    public static final String APP_ID = "10048014";
    public static final String API_KEY = "0Tc8ocXCknrVCIme5dr6mDTe";
    public static final String SECRET_KEY = "VodFiNWN77eGERNUixkHnKCwX245qFxi";

    public static void main(String[] args) {
        // 初始化一个NLPClient
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        NLPLexer(client);

        wordembedding(client);

        wordSimEmbedding(client);
    }

    /**
     * 语法分析
     * @param client
     */
    public static  void NLPLexer(AipNlp client) {
        String text = "百度是一家高科技公司";
        JSONObject response = client.lexer(text);
        //System.out.println(response.toString());
    }

    /**
     * 中文词向量表示
     * @param client
     */
    public static void wordembedding(AipNlp client) {
        // 获取一个词的词向量
        JSONObject response = client.wordEmbedding("百度");
        //System.out.println(response.toString());

    }

    /**
     * 词意相似度
     * @param client
     */
    public static void wordSimEmbedding(AipNlp client) {
        // 获取两个词的相似度结果
        JSONObject response = client.wordSimEmbedding(" 张亚飞", "刘川");
        System.out.println(response.toString());
    }

}
