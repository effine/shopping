package cn.effine;

import org.apache.http.client.utils.URLEncodedUtils;

import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;

/**
 * @author effine  Email: zhangyafei#co-mall.com
 * @version 3.2.1
 * @company 北京科码先锋软件技术有限公司@版权所有
 * @since 2017-06-20 11:05
 */
public class DecodeTest {

    public static void main(String[] args) throws UnsupportedEncodingException {


        System.out.println(URLEncoder.encode("土", "GBK"));
        System.out.println(URLDecoder.decode("%253A97%25E7%2589%258C%26fq%3D", "UTF-8"));



    }
}
