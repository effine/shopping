/**
 * @author effine
 * @Date 2015年11月13日  上午12:00:09
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.dao;

import java.io.InputStream;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class AbstractDao {

	// 数据库连接方式一
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

	// 数据库连接方式二
	/*
	 * 获取数据库访问链接
	 */
	public static SqlSession getSqlSession() {
		SqlSession session = null;
		try {
			InputStream stream = Resources.getResourceAsStream("configuration.xml");
			// 可以根据配置的相应环境读取相应的数据库环境
			// SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(
			// stream, "development");
			SqlSessionFactory factory = new SqlSessionFactoryBuilder()
					.build(stream);
			session = factory.openSession();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return session;
	}

	/*
	 * 获取数据库访问链接
	 */
	public static void closeSession(SqlSession session) {
		session.close();
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
