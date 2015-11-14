/**
 * @author effine
 * @date 2014年10月1日  下午11:17:23
 */

package cn.effine.dao.mybatis;

public interface UserDao {

	/**
	 * 用户注册
	 *
	 * @param username
	 *            用户名
	 * @param passwd
	 *            密码
	 * @return 是否注册成功
	 */
	boolean signup(String username, String passwd);

	/**
	 * 用户登录
	 *
	 * @param username
	 *            用户名
	 * @param passwd
	 *            密码
	 * @return 是否登录成功
	 */
	boolean signin(String username, String passwd);

	/**
	 * 用户注销
	 *
	 * @param uid
	 *            用户ID
	 * @return 是否注销成功
	 */
	boolean signout(int uid);

	/**
	 * 删除账户
	 *
	 * @return
	 */
	boolean killAccount();
}
