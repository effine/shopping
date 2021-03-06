/**
 * @author effine
 * @date 2014年11月4日  下午10:55:05
 */

package cn.effine.controller;

import cn.effine.model.User;
import cn.effine.service.UserService;
import cn.effine.utils.AlgorithmEnum;
import cn.effine.utils.EncryptUtils;
import cn.effine.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 * 用户操作
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param user 用户Model
     * @return
     */
    @RequestMapping(value = "signup", method = RequestMethod.PUT)
    @ResponseBody
    public String signup(User user) {
        String current = TimeUtils.getCurrentTime();
        user.setPasswd(EncryptUtils.encryptString(user.getPasswd(), current, AlgorithmEnum.BCRYPT));
        user.setSignupTime(current);
        boolean status = userService.signup(user);
        return String.valueOf(status);
    }

    /**
     * 用户登录
     *
     * @param request
     * @param response
     * @param username 用户名[邮箱|昵称|手机号]
     * @param password 密码
     * @param isAuto   是否自动登录[0否|1是]
     * @return
     */
    // TODO [邮箱|昵称|手机号]登录
    @RequestMapping(value = "signin", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> signin(HttpServletRequest request, HttpServletResponse response,
                                      String username, String password, int isAuto) {
        Map<String, Object> map = new HashMap<>(2);
        boolean isSignin = false;
        // 自动登录
        if (1 == isAuto) {
            // 将用户名密码放入Cookie
            // 到期时间：7天
            int expiry = 60 * 60 * 24 * 7;
            String host = request.getServerName();
            Cookie cookie = new Cookie("COOKIE_USERNAME", username);
            cookie.setPath("/");
            cookie.setDomain(host);
            // 设置cookie过期时间(秒)
            cookie.setMaxAge(expiry);

            // 密码MD5加密并保存cookie
            cookie = new Cookie("COOKIE_PASSWD", EncryptUtils.encryptString(password, AlgorithmEnum.MD5));
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setDomain(host);
            cookie.setMaxAge(expiry);
            response.addCookie(cookie);

            isSignin = userService.signin(username, password);
        }

        // 非自动登录
        if (0 == isAuto) {
            isSignin = userService.signin(username, password);
        }

        if (isSignin) {
            map.put("msg", "登录成功");
        } else {
            map.put("msg", "登录失败");
        }
        map.put("code", 200);
        return map;
    }

    /**
     * 用户注销
     *
     * @param request
     * @param response
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
                if (2 == execNo) {
                    break; // 不再循环cookis后面的内容
                }
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
    @ResponseBody
    public Map<String, Object> killAccount() {
        Map<String, Object> map = new HashMap<>(3);
        map.put("status", 1);
        Map<String, Object> dataMap = new HashMap<>(5);
        dataMap.put("name", "effine");
        dataMap.put("age", 25);
        dataMap.put("nikename", "effine");
        dataMap.put("email", "test@163.com");
        map.put("info", dataMap);

        Map<String, Object> testMap = new HashMap<>(5);
        testMap.put("nullval", "");
        testMap.put("0val", 0);
        List<String> lsit = null;
        testMap.put("nulllist", lsit);
        String str = null;
        testMap.put("nullstring", str);
        map.put("test", testMap);
        return map;
    }
}