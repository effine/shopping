/**
 * @author effine
 * @Date 2015年11月16日  下午3:28:02
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package com.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class TestController {

	@RequestMapping("topage")
	public String toPage() {
		System.out.println("---------------");
		return "user";
	}

}
