
/**
 * @author effine
 * @Date 2015年4月13日  上午11:15:54
 * @email verphen#gmail.com
 */

package cn.effine.test.service.impl;

import cn.effine.test.model.User;
import cn.effine.test.service.UserService;

public class UserServiceImpl implements UserService{

	public User read(String name, String passwd) {
		if(name.equals("effine") && passwd.equals("aichuan")){
			User user =new User();
			user.setId(1);
			user.setName(name);
			user.setPasswd(passwd);
			return user;
		}
		return null;
	}

	public User read(int id) {
		User user =new User();
		user.setId(1);
		user.setName("effine");
		user.setPasswd("aichuan");
		return user;
	}
}


