package cn.effine.service;

import java.util.List;

import main.java.com.effine.model.User;

public interface UserManager {

	public User getUser(String id);
	
	public List<User> getAllUser();
	
	public void addUser(User user);
	
	public boolean delUser(String id);
	
	public boolean updateUser(User user);
	
	public boolean checkUid(String uid);
	
	public boolean IsExistByUserame(String username);
	
	public User getUserByUsername(String username);
}
