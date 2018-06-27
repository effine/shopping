/**
 * @author effine
 * @email iballader#gmail.com
 * @date Jul 6, 2016  10:51:43 PM
 * @site http://effine.cn
 * @since 1.0
 */

package cn.effine.lab.md5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

public class Md5Utils {

    private static final Logger logger = LoggerFactory.getLogger(Md5Utils.class);

    /**
     * 将字符串MD5加密
     *
     * @param source 需要MD5加密的字符串,若为空则返回null
     * @return 加密后的字符串
     */
    public final static String md5(String source) {

        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] byteArr = source.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("md5");
            // 使用指定的字节更新摘要
            mdInst.update(byteArr);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
