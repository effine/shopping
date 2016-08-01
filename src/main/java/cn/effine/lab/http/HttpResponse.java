/**
 * @author  effine
 * @email   iballader#gmail.com
 * @date    Aug 1, 2016  11:53:29 PM
 * @site    http://effine.cn
 * @since   1.0
 */

package cn.effine.lab.http;

import com.alibaba.fastjson.JSONObject;

public class HttpResponse {

	/**
	 * Http状态码
	 */
	private int httpCode;

	/**
	 * Http请求响应信息
	 */
	private JSONObject response;

	/**
	 * Http登录请求返回的cookie
	 */
	private String cookie;

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public JSONObject getResponse() {
		return response;
	}

	public void setResponse(JSONObject response) {
		this.response = response;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

}
