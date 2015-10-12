/**
 * @author effine
 * @Date 2015年4月13日  上午11:15:38
 * @email verphen#gmail.com
 */

package cn.effine.test.service;

import cn.effine.test.model.User;


public interface UserService {

	User read(String name, String passwd);

	User read(int id);
}
