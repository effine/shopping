/**
 * @author effine
 * @Date 2015年4月13日  上午11:17:15
 * @email verphen#gmail.com
 */

package cn.effine.test.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 367647147839906259L;

	private int id;
	private String name;
	private String passwd;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
