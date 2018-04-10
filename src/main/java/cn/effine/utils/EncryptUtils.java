/**
 * @author effine
 * @Date 2015年12月22日  下午11:43:57
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */
package cn.effine.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 *
 * @author effine
 * @Date 2017-10-15 20:37
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */
public class EncryptUtils {

    /**
     * 全局数组
     */
    private static final String[] DIGITS = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
    private static Logger logger = Logger.getLogger(EncryptUtils.class);

    private EncryptUtils() {
    }

    /**
     * 按指定算法加密字符串
     *
     * @param source    源字符串
     * @param algorithm 加密算法
     * @return 加密完成字符串
     */
    public static String encryptString(String source, AlgorithmEnum algorithm) {
        switch (algorithm) {
            case MD5:
                return encryptMD5(source);
            case BCRYPT:
                return BCrypt.hashpw(source, BCrypt.gensalt());
            default:
                return null;
        }
    }

    /**
     * 按指定算法加密字符串
     *
     * @param source    源字符串
     * @param salt      加密盐
     * @param algorithm 加密算法
     * @return 加密完成字符串
     */
    public static String encryptString(String source, String salt, AlgorithmEnum algorithm) {
        switch (algorithm) {
            case MD5:
                return encryptMD5(source, salt);
            case BCRYPT:
                return BCrypt.hashpw(source, salt);
            default:
                return null;
        }
    }

    /**
     * MD5加密字符串
     *
     * @param source 待加密字符串
     * @return 加密完成字符串
     */
    private static String encryptMD5(String source) {
        if (StringUtils.isNotBlank(source)) {
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance(AlgorithmEnum.MD5.name());
            } catch (NoSuchAlgorithmException e) {
                logger.error(e);
            }
            if (null != md) {
                byte[] byteArr = source.getBytes();
                md.update(byteArr);
                byte[] digest = md.digest(byteArr);
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < digest.length; i++) {
                    stringBuilder.append(DIGITS[(digest[i] & 0xf0) >>> 4]);
                    stringBuilder.append(DIGITS[digest[i] & 0x0f]);
                }
                return stringBuilder.toString();
            }
        }
        return null;
    }

    /**
     * MD5加盐加密
     *
     * @param source 待加密字符串
     * @param salt   盐
     * @return
     */
    private static String encryptMD5(String source, String salt) {
        if (StringUtils.isNotBlank(source)) {
            return encryptMD5(source + salt);
        }
        return null;
    }
}
