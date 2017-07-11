package cn.effine;

import org.apache.http.client.utils.URLEncodedUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author effine  Email: zhangyafei#co-mall.com
 * @version 3.2.1
 * @company 北京科码先锋软件技术有限公司@版权所有
 * @since 2017-06-20 11:05
 */
public class DecodeTest {

    public static void main(String[] args) throws UnsupportedEncodingException {

        java.lang.String t = "牛奶,苹果";
        java.lang.String t1 = ",";

        System.out.println(URLEncoder.encode(t, "GBK"));
        System.out.println(URLEncoder.encode(t1, "GBK"));
        System.out.println(URLDecoder.decode("%C5%A3%C4%CC", "GBK"));
    }
}
