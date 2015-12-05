
/**
 * @author effine
 * @Date 2015年10月13日  下午2:01:02
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.effine.dao.UserDao;
import cn.effine.model.User;
import cn.effine.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;

	@Override
	public boolean signup(User user) {
		return userDao.signup(user);
	}

	@Override
	public boolean signin(String username, String passwd) {
		return userDao.signin(username, passwd);
	}

	@Override
	public boolean signout(int uid) {
		return userDao.signout(uid);
	}

	@Override
	public boolean killAccount() {
		
		return false;
	}

}


