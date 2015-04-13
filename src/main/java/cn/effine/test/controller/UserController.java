/**
 * @author effine
 * @Date 2015年4月13日  上午11:15:02
 * @email verphen#gmail.com
 */

package cn.effine.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.effine.test.model.User;
import cn.effine.test.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService service;
	
	public String user(int id, ModelMap model) {
		User user = service.read(id);
		model.addAttribute("user",user);
		return "test";
	}
}
