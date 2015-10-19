package cn.effine.todo.oauth.dao;

import java.util.List;

import cn.effine.model.User;

public interface UserDao {

	public User getUser(String id);
	
	public List<User> getAllUser();
	
	public void addUser(User user);
	
	public boolean delUser(String id);
	
	public boolean updateUser(User user);
	
	public boolean checkUid(String uid);
	
	public boolean IsExistByUserame(String username);
	
	public User getUserByUsername(String username);
}
