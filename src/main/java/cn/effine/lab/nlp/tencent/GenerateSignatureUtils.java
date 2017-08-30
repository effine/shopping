package cn.effine.lab.nlp.tencent;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 生产签名串工具类
 *
 * @author effine  Email: iballader#gmail.com
 */
public class GenerateSignatureUtils {


    /**
     * 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串
     *
     * @param param 要排序的Map对象
     * @return
     */
    public static String sortParam(Map<String, Object> param) {

        try {
            List<Map.Entry<String, Object>> infoIds = new ArrayList<Map.Entry<String, Object>>(param.entrySet());

            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, Object>>() {
                @Override
                public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });

            // 构造URL 键值对的格式
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<String, Object> item : infoIds) {
                if (StringUtils.isNotBlank(item.getKey())) {

                    String key = item.getKey();

                    // 替换参数中的下划线为"."
                    String value = String.valueOf(item.getValue()).replace("_", ".");

                    stringBuilder.append(key);
                    stringBuilder.append("=");
                    stringBuilder.append(value);
                    stringBuilder.append("&");
                }

            }

            return stringBuilder.substring(0, stringBuilder.length() - 1);
        } catch (Exception e) {
            return null;
        }
    }


    public static void main(String[] args) throws Exception {

        // 文档：https://www.qcloud.com/document/product/271/2053

        StringBuilder resultStr = new StringBuilder();

        // 2.2. 拼接请求字符串
        String SecretId = "AKID555wjYkgcQWhu3luDLNFF3fAwnknQT6a";
        String SecretKey = "66xeBcyX2OZW4UTexFrdMyedT9iLpBgE";

        // 字典序列排序
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("Action", "TextSentiment");
        paramsMap.put("SecretId", SecretId);
        paramsMap.put("Timestamp", System.currentTimeMillis());
        paramsMap.put("Nonce", new Random().nextInt(10000));
        paramsMap.put("Region", "gz");

        paramsMap.put("content", "双万兆服务器就是好，只是内存小点");

        String param = sortParam(paramsMap);
        System.out.println("> 请求字符串：" + param);

        // 2.3. 拼接签名原文字符串 (请求方法 + 请求主机 +请求路径 + ? + 请求字符串)
        String method = "GET";
        String host = "https://wenzhi.api.qcloud.com";
        String path = "/v2/index.php";

        StringBuilder sourceStr = new StringBuilder();
        sourceStr.append(method);
        sourceStr.append(host);
        sourceStr.append(path);
        sourceStr.append("?");
        sourceStr.append(param);


        // 2.4. 生成签名串
        String signStr = HmacSHA1Encryption.HmacSHA1Encrypt(sourceStr.toString(), SecretKey);
        System.out.println("> 生产的签名串：" + signStr);

        // 使用 Base64 进行编码
        signStr = new BASE64Encoder().encode(signStr.getBytes("utf-8"));
        System.out.println("> 生产的签名串使用 Base64 编码：" + signStr);


        // 使用 URL 进行编码
        signStr = URLEncoder.encode(signStr, "utf-8");
        System.out.println("> URL 编码：" + signStr);

        sourceStr.append("&Signature=");
        sourceStr.append(signStr);

        String urlAndParam = sourceStr.toString().replace(method, "");
        System.out.println("最终请求方法URL: " + urlAndParam);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(urlAndParam);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();


        System.out.println("\n\n\n响应内容：" + unicodeToChina(EntityUtils.toString(entity)));
        System.out.println("\n\n\n");

        httpClient.close();
        response.close();


    }


    /**
     * Unicode 转为汉字
     *
     * @param str
     * @return
     */
    public static String unicodeToChina(String str) {
        Charset set = Charset.forName("UTF-16");
        Pattern p = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
        Matcher m = p.matcher(str);
        int start = 0;
        int start2 = 0;
        StringBuffer sb = new StringBuffer();
        while (m.find(start)) {
            start2 = m.start();
            if (start2 > start) {
                String seg = str.substring(start, start2);
                sb.append(seg);
            }
            String code = m.group(1);
            int i = Integer.valueOf(code, 16);
            byte[] bb = new byte[4];
            bb[0] = (byte) ((i >> 8) & 0xFF);
            bb[1] = (byte) (i & 0xFF);
            ByteBuffer b = ByteBuffer.wrap(bb);
            sb.append(String.valueOf(set.decode(b)).trim());
            start = m.end();
        }
        start2 = str.length();
        if (start2 > start) {
            String seg = str.substring(start, start2);
            sb.append(seg);
        }
        return sb.toString();
    }
}
