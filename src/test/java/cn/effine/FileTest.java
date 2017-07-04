package cn.effine;

import java.io.IOException;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;

/**
 * @author effine  Email: zhangyafei#co-mall.com
 * @version 3.2.1
 * @company 北京科码先锋软件技术有限公司@版权所有
 * @since 2017-06-20 11:48
 */
public class FileTest {

    public static void main(String[] args) throws IOException {

        // URLEncoder 编码
        String enTarger = "奶粉";
        String encoder = URLEncoder.encode(enTarger, "utf-8");
        System.out.println("编码：" + encoder);


        // URLDecoder 解码
        String deTarger = "0k%E7%89%9B%E5%A5%B6";
        String decoder = URLDecoder.decode(deTarger, "utf-8");
        System.out.println("解码：" + decoder);





    }
}
