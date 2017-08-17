package cn.effine;

import org.apache.http.client.utils.URLEncodedUtils;

import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecodeTest {

    public static void main(String[] args) throws UnsupportedEncodingException {


        System.out.println(URLEncoder.encode("土", "GBK"));
        System.out.println(URLDecoder.decode("*%3A*", "UTF-8"));

        String str = "90,12";

        String regex = "[A-Za-z0-9,\\u4e00-\\u9fa5]+$";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        if (m.find()) {
            System.out.println(true);
        }else
            System.out.println(false);



    }
}
