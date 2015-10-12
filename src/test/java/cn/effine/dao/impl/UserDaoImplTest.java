/**
 * @author verphen
 * @date 2014年10月2日  上午12:21:12
 */

package cn.effine.dao.impl;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.effine.inter.IUserOperation;
import cn.effine.model.User;

public class UserDaoImplTest {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;

	static {
		try {
			reader = Resources.getResourceAsReader("Configuration.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}

	public static void main(String[] args) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			//==================== 第二部分：面向接口编程   ==============================//
			//使用时必须先注册：sqlSessionFactory.getConfiguration().addMapper(IUserOperation.class)；
			//然后必须在接口上添加注释:@Select("select * from user")
			IUserOperation userOperation = session.getMapper(IUserOperation.class);
			User user1 = userOperation.selectUserByID(1);
			System.out.println(user1.getName());
			
			List<User> list1 = userOperation.selectUserList("verphen");
			System.out.println(list1.size());
			
			/* 插入数据 */
			//User userAdd = new User();
			//userAdd.setName("test1");
			//userAdd.setPassword("test1");
			//userOperation.add(userAdd);
			//必须提交事务,写入数据库
			//session.commit();
			
			/* 更新数据  */
			//User userUpdate = new User();
			//userUpdate.setId(1);
			//userUpdate.setName("test0");
			//userUpdate.setPassword("test0");
			//userOperation.update(userUpdate);
			//session.commit();
			
			/*删除数据*/
			//userOperation.delete(1);
			//session.commit();    
			
			
			/* 两表联合查询 */
			userOperation.getUserArticles(1);
			
			
		} finally {
			session.close();
		}
	}
	
	public boolean add(){
		
		
		return false;
	}
}
