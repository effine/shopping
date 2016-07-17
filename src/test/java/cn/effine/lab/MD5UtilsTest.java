/**
 * @author  effine
 * @email   iballader#gmail.com
 * @date    Jul 6, 2016  11:03:14 PM
 * @site    http://effine.cn
 * @since   1.0
 */

package cn.effine.lab;

import org.junit.Assert;
import org.junit.Test;

import cn.effine.lab.md5.MD5Utils;

public class MD5UtilsTest {

	@Test
	public void MD5Test() {
		String source = "effine";
		// 验证同样的字符串进行MD5加密之后是否得到相同的密文
		System.out.println("密文: " + MD5Utils.MD5(source));
		Assert.assertEquals(MD5Utils.MD5(source), MD5Utils.MD5(source));
	}

}
