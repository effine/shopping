package cn.effine.lab.nlp.tencent;

import cn.effine.lab.nlp.tencent.qcloud.Module.Wenzhi;
import cn.effine.lab.nlp.tencent.qcloud.QcloudApiModuleCenter;
import cn.effine.lab.nlp.tencent.qcloud.Utilities.Json.JSONArray;
import cn.effine.lab.nlp.tencent.qcloud.Utilities.Json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Demo {

    private static String SecretId = "AKID9A3HOLgi8sZ4MSpfFqGUVKyb7jATrw69";
    private static String SecretKey = "41pRkz3Um02SnxBKWWsLeoqIqRxHPNCw";

    private static String context = "你是不是有病";

    public static void main(String[] args) {

		/* 如果是循环调用下面举例的接口，需要从此处开始你的循环语句。切记！ */
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        config.put("SecretId", SecretId);
        config.put("SecretKey", SecretKey);
        /* 请求方法类型 POST、GET */
        config.put("RequestMethod", "GET");
        /* 区域参数，可选: gz:广州; sh:上海; hk:香港; ca:北美;等。 */
        config.put("DefaultRegion", "bj");

		/*
         * 你将要使用接口所在的模块，可以从 官网->云api文档->XXXX接口->接口描述->域名
		 * 中获取，比如域名：cvm.api.qcloud.com，module就是 new Cvm()。
		 */
		/*
		 * 示例：DescribeInstances
		 * 的api文档地址：http://www.qcloud.com/wiki/v2/DescribeInstances
		 */
        QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Wenzhi(), config);

        TreeMap<String, Object> params = new TreeMap<String, Object>();
		/* 将需要输入的参数都放入 params 里面，必选参数是必填的。 */
		/* DescribeInstances 接口的部分可选参数如下 */
        params.put("content", context);
        params.put("type", 1);
		/*在这里指定所要用的签名算法，不指定默认为HmacSHA1*/
        //params.put("SignatureMethod", "HmacSHA256");
		
		/* generateUrl方法生成请求串,可用于调试使用 */
        //System.out.println(module.generateUrl("DescribeInstances", params));
        String result = null;
        try {
			/* call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 */
            result = module.call("TextSentiment", params);
            JSONObject json_result = new JSONObject(result);
            System.out.println(json_result);
        } catch (Exception e) {
            System.out.println("error..." + e.getMessage());
        }

    }


    public static Map<String, String> getTextDependency(String context) {
		/* 如果是循环调用下面举例的接口，需要从此处开始你的循环语句。切记！ */
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        config.put("SecretId", SecretId);
        config.put("SecretKey", SecretKey);
		/* 请求方法类型 POST、GET */
        config.put("RequestMethod", "GET");
		/* 区域参数，可选: gz:广州; sh:上海; hk:香港; ca:北美;等。 */
        config.put("DefaultRegion", "bj");

		/*
		 * 你将要使用接口所在的模块，可以从 官网->云api文档->XXXX接口->接口描述->域名
		 * 中获取，比如域名：cvm.api.qcloud.com，module就是 new Cvm()。
		 */
		/*
		 * 示例：DescribeInstances
		 * 的api文档地址：http://www.qcloud.com/wiki/v2/DescribeInstances
		 */
        QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Wenzhi(), config);

        TreeMap<String, Object> params = new TreeMap<String, Object>();
		/* 将需要输入的参数都放入 params 里面，必选参数是必填的。 */
		/* DescribeInstances 接口的部分可选参数如下 */
        params.put("content", context);
		/*在这里指定所要用的签名算法，不指定默认为HmacSHA1*/
        //params.put("SignatureMethod", "HmacSHA256");

		/* generateUrl方法生成请求串,可用于调试使用 */
        //System.out.println(module.generateUrl("DescribeInstances", params));
        String result = null;
        try {
			/* call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 */
            result = module.call("TextDependency", params);
            JSONObject json_result = new JSONObject(result);
            System.out.println(json_result);

            String resultJson = String.valueOf(json_result);
            Map<String, String> map = new HashMap<>();
            map.put("source", resultJson);

            StringBuilder one = new StringBuilder();
            JSONArray keywords = json_result.getJSONArray("keywords");
            for (int i = 0; i < keywords.length(); i++) {
                JSONArray jsonArray = (JSONArray) keywords.get(i);
                if (null != jsonArray) {
                    for (int j = 0; j < jsonArray.length(); j++) {
                        JSONObject object = (JSONObject) jsonArray.get(j);
                        one.append(object.get("word"));
                        one.append("，");
                    }
                }
            }

            map.put("one", one.substring(0, one.length() - 1));
            return map;
        } catch (Exception e) {
            System.out.println("error..." + e.getMessage());
        }
        return null;
    }
}
