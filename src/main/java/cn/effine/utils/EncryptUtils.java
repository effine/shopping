/**
 * @author effine
 * @Date 2015年12月22日  下午11:43:57
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */
package cn.effine.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 */
public class EncryptUtils {

    private static Logger logger = Logger.getLogger(EncryptUtils.class);

    // MD5加密算法
    private static final String ALGORITHM_MD5 = "MD5";
    // 全局数组
    private static final String[] strDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    private EncryptUtils() {
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] b) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            stringBuilder.append(strDigits[(b[i] & 0xf0) >>> 4]);
            stringBuilder.append(strDigits[b[i] & 0x0f]);
        }
        return stringBuilder.toString();
    }

    /**
     * 按指定算法加密字符串
     *
     * @param source    源字符串
     * @param algorithm 加密算法
     * @return 加密完成字符串
     */
    public static String encryptString(String source, AlgorithmEnum algorithm) {
        if (StringUtils.isNotBlank(source)) {
            switch (algorithm) {
                case MD5:
                    try {
                        MessageDigest md = MessageDigest.getInstance(algorithm.name());
                        byte[] byteArr = source.getBytes();
                        md.update(byteArr);
                        return byteToString(md.digest(byteArr));
                    } catch (NoSuchAlgorithmException e) {
                        logger.error(e);
                    }
                case BCRYPT:
                    // TODO 待实现
            }
        }
        return null;
    }

}
