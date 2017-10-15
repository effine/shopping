/**
 * @author effine
 * @Date 2015年11月13日  下午5:34:17
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.dao.impl.spring;

import cn.effine.dao.SigninHistoryDaoTest;
import org.junit.Test;

public class SigninHistoryTest extends DaoSupport implements SigninHistoryDaoTest {

    @Override
    @Test
    public void getSigninHistory() {
//		String sql = "select * from signin_history where id =?";
//		Map<String,Object> obj = getJdbcTemplate().queryForMap(sql, 1);
//		System.out.println(obj);
    }

    @Test
    public void countSigninHistory() {
//		String sql = "select count(id) from signin_history";
//		int times = getJdbcTemplate().queryForObject(sql, Integer.class);
//		System.out.println(times);
    }

}
