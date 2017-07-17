package cn.effine;

import org.apache.http.client.utils.URLEncodedUtils;

import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author effine  Email: zhangyafei#co-mall.com
 * @version 3.2.1
 * @company 北京科码先锋软件技术有限公司@版权所有
 * @since 2017-06-20 11:05
 */
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


    public static  boolean test(char input) {
        if(input >= '0' && input <= '9'){
            return true;

        }else if((input >= 'a' && input <= 'z')
                || (input >= 'A' && input <= 'Z')){
            return true;

        }else {
            Character.UnicodeBlock ub = Character.UnicodeBlock.of(input);

            if(ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                    || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                    || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A){
                //目前已知的中文字符UTF-8集合
                return true;

            }else if(ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS //全角数字字符和日韩字符
                    //韩文字符集
                    || ub == Character.UnicodeBlock.HANGUL_SYLLABLES
                    || ub == Character.UnicodeBlock.HANGUL_JAMO
                    || ub == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO
                    //日文字符集
                    || ub == Character.UnicodeBlock.HIRAGANA //平假名
                    || ub == Character.UnicodeBlock.KATAKANA //片假名
                    || ub == Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS){
                return true;

            }
        }
        //其他的不做处理的字符
        return false;
    }
}
