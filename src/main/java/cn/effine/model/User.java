/**
 * @author effine
 * @date 2014年10月2日  上午12:15:32
 */

package cn.effine.model;

public class User {
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