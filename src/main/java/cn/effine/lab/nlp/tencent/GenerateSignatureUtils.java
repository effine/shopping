package cn.effine.lab.nlp.tencent;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;

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


    public static void main(String[] args) throws Exception {

        // 文档：https://www.qcloud.com/document/product/271/2053
        // 腾讯提供的调试接口：http://182.254.136.27/yunapi/tools/


        // 2.2. 拼接请求字符串 (需要去腾讯云查看SecretId、SecretKey添加此处)
        // 密钥生成：https://console.qcloud.com/capi
        String SecretId = "AKID9A3HOLgi8sZ4MSpfFqGUVKyb7jATrw69";
        String SecretKey = "41pRkz3Um02SnxBKWWsLeoqIqRxHPNCw";

        // 字典序列排序
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("Action", "TextDependency");
        paramsMap.put("Region", "bj");

        String current = String.valueOf(System.currentTimeMillis());
        paramsMap.put("Timestamp", current.substring(0, current.length() - 3));
        paramsMap.put("Nonce", new Random().nextInt(99999999));
        paramsMap.put("SecretId", SecretId);

        paramsMap.put("content", "JASON捷森五种水果麦片");

        String param = sortParam(paramsMap);
        System.out.println("> 请求字符串：" + param);

        // 2.3. 拼接签名原文字符串 (请求方法 + 请求主机 +请求路径 + ? + 请求字符串)
        String method = "GET";
        String host = "wenzhi.api.qcloud.com";
        String path = "/v2/index.php?";

        StringBuilder sourceStr = new StringBuilder();
        sourceStr.append(method);
        sourceStr.append(host);
        sourceStr.append(path);

        // 2.4. 生成签名串
        String signStr = HmacSHA1Encryption.HmacSHA1Encrypt(sourceStr.toString(), SecretKey);
        System.out.println("> 生产的签名串：" + signStr);

        // 使用 Base64 进行编码
        signStr = new BASE64Encoder().encode(signStr.getBytes("utf-8"));
        System.out.println("> 生产的签名串使用 Base64 编码：" + signStr);

        System.out.println("\n\n\n\n");

        // 构建 http 请求的url
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("https://");
        urlBuilder.append(host);
        urlBuilder.append(path);
        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            String key = entry.getKey();
            Object value = URLEncoder.encode(String.valueOf(paramsMap.get(key)), "utf-8");
//            Object value = paramsMap.get(key);
            urlBuilder.append(key);
            urlBuilder.append("=");
            urlBuilder.append(value);
            urlBuilder.append("&");
        }
        urlBuilder.append("Signature=");
        urlBuilder.append(URLEncoder.encode(signStr, "utf-8"));
//        urlBuilder.append(signStr);

        String url = urlBuilder.toString();
        System.out.println("\n\n 请求URL: " + url + "\n\n");
        // 发起 http 请求
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
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
}
