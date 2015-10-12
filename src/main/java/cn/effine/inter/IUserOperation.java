/**
 * @author verphen
 * @date 2014年10月3日  下午2:16:21
 */

package cn.effine.inter;

import java.util.List;

import cn.effine.model.Article;
import cn.effine.model.User;

public interface IUserOperation {

	public User selectUserByID(int id);

	public List<User> selectUserList(String usrname);

	public void add(User user);
	
	public void update(User user);
	
	public void delete(int id);
	
	public List<Article> getUserArticles(int id);
}
