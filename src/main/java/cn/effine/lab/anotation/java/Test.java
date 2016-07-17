/**
 * @author  effine
 * @email   iballader#gmail.com
 * @date    Jul 17, 2016  10:40:05 AM
 * @site    http://effine.cn
 * @since   1.0
 */

package cn.effine.lab.anotation.java;

public class Test {

	@SuppressWarnings("deprecation")
	public void testJavaAnnotation() {
		UserDao dao = new UserDaoImpl();
		dao.delUser();
	}
}
