/**
 * @author effine
 * @date 2014年10月4日  上午11:23:27
 */

package cn.effine.model;

import java.io.Serializable;

public class Article implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private User user;
	private String title;
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
