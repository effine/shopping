package cn.effine.lab.nlp.tencent;

import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Encoder;

import java.net.URLEncoder;
import java.util.*;

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
        paramsMap.put("Action", "LexicalAnalysis");
        paramsMap.put("SecretId", SecretId);
        long currentTime = System.currentTimeMillis();
        paramsMap.put("Timestamp", "1504104808809");
        System.out.println("> 当前时间毫秒数：" + currentTime);

        paramsMap.put("Nonce", 345122);
        paramsMap.put("Region", "gz");

        paramsMap.put("text", "我爱洗澡");
        paramsMap.put("code", 2097152);

        String param = sortParam(paramsMap);
        System.out.println("> 请求字符串：" + param);

        // 2.3. 拼接签名原文字符串 (请求方法 + 请求主机 +请求路径 + ? + 请求字符串)
        String method = "GET";
        String host = "cvm.api.qcloud.com";
        String path = "/v2/index.php";

        StringBuilder sourceStr = new StringBuilder();
        sourceStr.append(method);
        sourceStr.append(host);
        sourceStr.append(path);
        sourceStr.append("?");
        sourceStr.append(param);


        // 2.4. 生成签名串
        String signStr = HmacSHA1Encryption.HmacSHA1Encrypt(URLEncoder.encode(sourceStr.toString(), "utf-8"), SecretKey);
        System.out.println("> 生产的签名串：" + signStr);

        // 使用 Base64 进行编码
        signStr = new BASE64Encoder().encode(signStr.getBytes("utf-8"));
        System.out.println("> 生产的签名串使用 Base64 编码：" + signStr);


        // 使用 URL 进行编码
        signStr = URLEncoder.encode(signStr, "utf-8");
        System.out.println("> URL 编码：" + signStr);


    }


}
