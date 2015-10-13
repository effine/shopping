/**
 * @author effine
 * @Date 2015年10月13日  下午2:01:31
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.dao.impl;

import org.springframework.stereotype.Repository;

import cn.effine.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

	@Override
	public boolean signup(String username, String passwd) {

		return false;
	}

	@Override
	public boolean signin(String username, String passwd) {
		if ("effine".equals(username) && "aichuan".equals(passwd)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean signout(int uid) {

		return false;
	}

}
