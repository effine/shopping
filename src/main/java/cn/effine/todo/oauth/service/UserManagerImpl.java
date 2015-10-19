package cn.effine.service;

import java.util.List;




import main.java.com.effine.dao.UserDao;
import main.java.com.effine.model.User;
public class UserManagerImpl implements UserManager {

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getUser(String id) {
		return userDao.getUser(id);
	}

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Override
	public boolean delUser(String id) {
		
		return userDao.delUser(id);
	}

	@Override
	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public boolean checkUid(String uid) {
		// TODO Auto-generated method stub
		
		return userDao.checkUid(uid);
	}

	@Override
	public boolean IsExistByUserame(String username){
		// TODO Auto-generated method stub
		return userDao.IsExistByUserame(username);
	}
	@Override
	public User getUserByUsername(String username){
		return userDao.getUserByUsername(username);
	}
}


