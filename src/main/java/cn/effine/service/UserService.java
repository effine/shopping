/**
 * @author effine
 * @date 2014年11月4日  下午10:56:33
 */

package cn.effine.service;

import cn.effine.model.User;

public interface UserService {

	/**
	 * 用户注册
	 *
	 * @param user
	 *            用户
	 * @return 是否注册成功
	 */
	boolean signup(User user);

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
