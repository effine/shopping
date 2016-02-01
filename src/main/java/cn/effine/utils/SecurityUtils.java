/**
 * @author effine
 * @Date 2015年12月22日  下午11:43:57
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 处理安全工具
 */
public class SecurityUtils {
	// MD5加密算法
	private final static String algorithm_MD5 = "MD5";
	// 全局数组 
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

	// 转换字节数组为16进制字串 
	private static String byteToString(byte[] b) {
		StringBuffer strBuffer = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			strBuffer.append(strDigits[(b[i] & 0xf0) >>> 4]);
			strBuffer.append(strDigits[b[i] & 0x0f]);
		}
		return strBuffer.toString();
	}

	/**
	 * 对字符串进行加密
	 *
	 * @param src
	 *            源字符串
	 * @param algorithm
	 *            加密算法；可以为空，默认为MD5
	 * @return 加密完成字符串
	 */
	public static String encryption(String src, String... algorithm) {
		MessageDigest md  = null;
		try {
			if(null == algorithm){
				md = MessageDigest.getInstance(algorithm_MD5);
			}else{
				// TODO 其他加密算法
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] byteArr = src.getBytes();
		md.update(byteArr);
		return byteToString(md.digest(byteArr));
	}
}
