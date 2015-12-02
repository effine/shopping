/**
 * @author effine
 * @Date 2015年11月13日  下午5:34:17
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.dao.impl.springJdbc;

import java.util.Map;

import org.junit.Test;

public class SigninHistoryTest extends AbstractSupportDao{

	@Test
	public void getSigninHistory() {
		Map<String,Object> obj = new SigninHistoryDaoImpl().getSigninHistory(1);
		System.out.println(obj);
	}
	
	@Test
	public void countSigninHistory() {
		String sql = "select count(id) from signin_history";
		int times = getJdbcTemplate().queryForObject(sql, Integer.class);
		System.out.println(times);
	}
}
