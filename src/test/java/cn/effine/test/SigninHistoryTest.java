/**
 * @author effine
 * @Date 2015年11月13日  下午4:29:08
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.test;

import org.apache.ibatis.session.SqlSession;

import cn.effine.dao.AbstractSupportDao;
import cn.effine.model.SigninHistory;

public class SigninHistoryTest {

	public static void getSigninHistory(int id) {
		SqlSession session = AbstractSupportDao.getSqlSession();
		SigninHistory history = session
				.selectOne(
						"cn.effine.ISigninHistoryOperation.selectSigninHistoryByID",
						id);
		session.close();

		System.out.println(history);

	}

	public static void main(String[] args) {
		getSigninHistory(1);
	}
}
