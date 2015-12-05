/**
 * @author effine
 * @date 2014年11月4日  下午10:55:05
 */

package cn.effine.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.effine.model.User;
import cn.effine.service.UserService;
import cn.effine.utils.JsonUtils;
import cn.effine.utils.MD5Utils;
import cn.effine.utils.TimeUtils;

/**
 * 用户操作
 */
@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * 用户注册
	 *
	 * @return
	 */
	@RequestMapping("signup")
	@ResponseBody
	public String signup(User user) {
		// TODO 密码进行加密
		user.setSignupTime(TimeUtils.getCurrentTime());
		boolean status = userService.signup(user);
		return String.valueOf(status);
	}

	/**
	 * 用户登录
	 *
	 * @param username
	 *            用户名[邮箱|昵称|手机号]
	 * @param passwd
	 *            密码
	 * @param isAuto
	 *            是否自动登录[0否|1是]
	 * @return
	 */
	// TODO effine [邮箱|昵称|手机号]登录
	@RequestMapping("signin")
	public String signin(HttpServletRequest request, HttpServletResponse response, 
			String username, String passwd, int isAuto) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSignin = false;
		// 自动登录
		if (1 == isAuto) {
			// 将用户名密码放入Cookie
			int expiry = 60 * 60 * 24 * 7; // 到期时间：7天
			String host = request.getServerName();
			Cookie cookie = new Cookie("COOKIE_USERNAME", username);
			cookie.setPath("/");
			cookie.setDomain(host);
			cookie.setMaxAge(expiry); // 设置cookie过期时间(秒)

			// 密码MD5加密并保存cookie
			cookie = new Cookie("COOKIE_PASSWD", MD5Utils.encode(passwd,
					"utf-8"));
			cookie.setPath("/");
			cookie.setDomain(host);
			cookie.setMaxAge(expiry);
			response.addCookie(cookie);

			isSignin = userService.signin(username, passwd);
		}

		// 非自动登录
		if (0 == isAuto) {
			isSignin = userService.signin(username, passwd);
		}

		if (isSignin)
			map.put("msg", "登录成功");
		else
			map.put("msg", "登录失败");
		map.put("code", 200);
		return JsonUtils.mapToJSONString(map);
	}

	/**
	 * 用户注销
	 *
	 * @return
	 */
	@RequestMapping("signout")
	public String signout(HttpServletRequest request,
			HttpServletResponse response) {
		String host = request.getServerName();

		// 用户注销删除cookie
		int execNo = 0;
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if ("COOKIE_USERNAME".equals(cookie.getName())) {
					cookie = new Cookie("COOKIE_USERNAME", "");
					cookie.setDomain(host);
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					execNo++;
				} else if ("COOKIE_PASSWD".equals(cookie.getName())) {
					cookie = new Cookie("COOKIE_PASSWD", "");
					cookie.setDomain(host);
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					execNo++;
				}
				if (2 == execNo)
					break; // 不再循环cookis后面的内容
			}
		}
		return null;
	}

	/**
	 * 删除账户
	 *
	 * @return
	 */
	@RequestMapping("kill")
	public String killAccount() {
		System.out.println("-----------");
		return null;
	}
}