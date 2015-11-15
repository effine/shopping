/**
 * @author effine
 * @Date 2015年11月13日  上午12:00:09
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.dao.impl.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class AbstractSupportDao {

	private static String configure_path = "mybatis-config.xml";
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
			// 方式1
			Reader reader = Resources.getResourceAsReader(configure_path);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
			// 方式2
			// InputStream stream = Resources.getResourceAsStream(configure_path);
			// sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 获取数据库访问会话session
	 */
	public static SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
	
	/*
	 * 返回操作记录消息
	 */
	public static void showMessages(CRUD_Enum type, int count) {
		switch (type) {
		case Add:
			System.out.println("添加了" + count + "条记录。");
			break;
		case Delete:
			System.out.println("删除了" + count + "条记录。");
			break;
		case Update:
			System.out.println("更新了" + count + "条记录。");
			break;
		case Query:
			System.out.println("匹配了" + count + "条记录。");
			break;
		case List:
			System.out.println("共有" + count + "条记录。");
			break;
		default:
			break;
		}
	}
}
