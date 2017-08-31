package cn.effine.lab.nlp.baidu;

import com.baidu.aip.nlp.AipNlp;
import org.json.JSONArray;
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



    //设置APPID/AK/SK (TODO 需要去百度云查看APP_ID、API_KEY、SECRET_KEY添加此处)
    public static final String APP_ID = "";
    public static final String API_KEY = "";
    public static final String SECRET_KEY = "";

    public static void main(String[] args) {
        // 初始化一个NLPClient
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        NLPLexer(client);

        // wordembedding(client);

//        wordSimEmbedding(client);
    }

    /**
     * 语法分析
     *
     * @param client
     */
    public static void NLPLexer(AipNlp client) {
        String text = "【自营】JASON捷森五种水果麦片1000g（德国进口 袋）";
        JSONObject response = client.lexer(text);

        StringBuilder one = new StringBuilder();
        StringBuilder two = new StringBuilder();

        JSONArray iterms = response.getJSONArray("items");
        for (int i=0; i< iterms.length(); i++){
            JSONObject jsonObject = iterms.getJSONObject(i);

            // 一级分词
            String item = jsonObject.getString("item");
            one.append(item);
            one.append(",");

            // 二级分词
            JSONArray basicWords = jsonObject.getJSONArray("basic_words");
            two.append(item);
            two.append(":");
            if(null != basicWords){
                StringBuilder temp = new StringBuilder();
                for (int j=0; j< basicWords.length();j++){
                    temp.append(basicWords.getString(j));
                    temp.append(",");
                }
                if(temp.length() > 0){
                    two.append(temp.substring(0, temp.length()-1));
                }
            }

            two.append("\n");
        }

        System.out.println(response.toString());

        System.out.println("\n\n\n 一级分词：" +  one.substring(0, one.length()-1));
        System.out.println("\n\n\n 二级分词： \n\n" + two.substring(0, two.length()-1));
    }

    /**
     * 中文词向量表示
     *
     * @param client
     */
    public static void wordembedding(AipNlp client) {
        // 获取一个词的词向量
        JSONObject response = client.wordEmbedding("好利友扁桃仁糖90g(韩国进口 袋)");
        System.out.println(response.toString());

    }

    /**
     * 词意相似度
     *
     * @param client
     */
    public static void wordSimEmbedding(AipNlp client) {
        // 获取两个词的相似度结果
        JSONObject response = client.wordSimEmbedding(" 张亚飞", "刘川");
        //System.out.println(response.toString());
    }

}
