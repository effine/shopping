/**
 * @author effine
 * @Date 2015年10月28日  上午10:44:47
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.test;

import java.io.UnsupportedEncodingException;

public class Test {

	public static void main(String[] args) {

		byte[]  barr= {};
		
		try {
			byte[] carr = new String(barr, "utf-8").getBytes();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		try {
			byte[] darr = barr.toString().getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
}