/**
 * @author effine
 * @Date 2015年10月28日  上午10:44:47
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.test;

import java.io.File;


public class TestCommons {

	public static void main(String[] args) {

		// ImagesUtils.compress("d:/test/yun.png", "d:/test/yun1.png", 0.11);
		
		// TODO MAC测试打印结果
		 System.out.println(File.separator);
		 System.out.println(File.separatorChar);
		 System.out.println(File.pathSeparator);
		 System.out.println(File.pathSeparatorChar);
		 
		 // windows测试结果：\   \   ;  ; 
		 // MAC测试结果：/  / :  : 
		 
	}
}