/**
 * @author effine
 * @Date 2015年11月13日  下午4:29:08
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.dao.impl.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import cn.effine.dao.SigninHistoryDaoTest;
import cn.effine.model.SigninHistory;

public class SigninHistoryTest  implements SigninHistoryDaoTest{

	@Test
	public void getSigninHistory() {
		SqlSession session = DaoSupport.getSqlSession();
		SigninHistory history = session .selectOne("cn.effine.ISigninHistoryOperation.selectSigninHistoryByID",1);
		session.close();
		System.out.println(history);
	}

	@Override
	public void countSigninHistory() {
		// TODO  unimplements method stub
	}
}
