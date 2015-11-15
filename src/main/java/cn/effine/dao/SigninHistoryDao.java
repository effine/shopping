/**
 * @author effine
 * @Date 2015年11月12日  下午11:16:01
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.dao;

import java.util.Map;

public interface SigninHistoryDao {

	/**
	 * 获取用户登录历史
	 *
	 * @param id
	 *            登录历史ID
	 * @return 历史登录信息
	 */
	// TODO effine 测试用例
	Map<String, Object> getSigninHistory(int id);

}
