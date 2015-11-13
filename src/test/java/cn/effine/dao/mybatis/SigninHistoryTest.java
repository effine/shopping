/**
 * @author effine
 * @Date 2015年11月13日  下午4:29:08
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.dao.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import cn.effine.dao.mybatis.AbstractSupportDao;
import cn.effine.model.SigninHistory;

public class SigninHistoryTest {

	@Test
	public void getSigninHistory() {
		SqlSession session = AbstractSupportDao.getSqlSession();
		SigninHistory history = session .selectOne("cn.effine.ISigninHistoryOperation.selectSigninHistoryByID",1);
		session.close();
		System.out.println(history);

	}
}
