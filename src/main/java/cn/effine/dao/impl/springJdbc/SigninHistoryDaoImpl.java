/**
 * @author effine
 * @Date 2015年11月15日  下午9:43:58
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.dao.impl.springJdbc;

import java.util.Map;

import cn.effine.dao.SigninHistoryDao;

public class SigninHistoryDaoImpl extends AbstractSupportDao implements SigninHistoryDao {

	@Override
	public Map<String, Object> getSigninHistory(int id) {
		String sql = "select * from signin_history where id =?";
		return getJdbcTemplate().queryForMap(sql, id);
	}

}
