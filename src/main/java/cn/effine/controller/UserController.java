/**
 * @author effine
 * @date 2014年11月4日  下午10:55:05
 */

package cn.effine.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.effine.service.UserService;
import cn.effine.utils.MD5Utils;

/**
 * 用户操作
 */
@RequestMapping("user")
@Controller
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * 用户注册
	 *
	 * @return
	 */
	@RequestMapping("signup")
	public String signup(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	/**
	 * 用户登录
	 *
	 * @param username
	 *            用户名
	 * @param passwd
	 *            密码
	 * @param isAuto
	 *            是否自动登录[0否|1是]
	 * @return
	 */
	@RequestMapping("signin")
	public String signin(HttpServletRequest request, HttpServletResponse response, String username, String passwd, int isAuto) {
		// 验证是否自动登录
		if(1 == isAuto){
			int expiry = 60*60*7; // 到期时间：7天
			String host = request.getServerName();
			Cookie cookie = new Cookie("COOKIE_USERNAME", username);
			cookie.setPath("/");
			cookie.setDomain(host);
			cookie.setMaxAge(expiry); // 设置cookie过期时间(秒)
			
			// 密码MD5加密并保持cookie
			cookie = new Cookie("COOKIE_PASSWD", MD5Utils.encode(passwd, "utf-8"));   
			cookie.setPath("/");  
			cookie.setDomain(host);  
			cookie.setMaxAge(expiry);  
			response.addCookie(cookie); 
		}
		return null;
	}

	/**
	 * 用户注销
	 *
	 * @return
	 */
	@RequestMapping("signout")
	public String signout(HttpServletRequest request, HttpServletResponse response) {
		String host = request.getServerName();
		
		// 用户注销删除cookie
		int execNo = 0;
		Cookie[] cookies = request.getCookies(); 
		for(Cookie cookie: cookies){
			if("COOKIE_USERNAME".equals(cookie.getName())){
				cookie = new Cookie("COOKIE_USERNAME", "");
				cookie.setDomain(host);
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				execNo ++ ;
			}else if("COOKIE_PASSWD".equals(cookie.getName())){
				cookie = new Cookie("COOKIE_PASSWD", "");
				cookie.setDomain(host);
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				execNo ++ ;
			}
			if(2 == execNo) break; // 不在循环cookis后面的内容
		}
		
		return null;
	}
}