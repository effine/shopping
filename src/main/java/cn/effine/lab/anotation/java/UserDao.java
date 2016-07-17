/**
 * @author  effine
 * @email   iballader#gmail.com
 * @date    Jul 17, 2016  10:38:41 AM
 * @site    http://effine.cn
 * @since   1.0
 */

package cn.effine.lab.anotation.java;

public interface UserDao {

	boolean updateUser();

	int addUser();

	@Deprecated
	boolean delUser();
}
