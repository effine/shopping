package cn.effine;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

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
