/**
 * @author effine
 * @Date 2015年12月5日  下午3:31:23
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

@Controller
public class ForwardController {

	@RequestMapping("{mapping}")
	public String forwardMethod(@RequestPart String mapping) {
		return mapping;
	}
}
