/**
 * @author effine
 * @Date 2015年12月5日  下午3:31:23
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转
 */
@Controller
public class ForwardController {

	@RequestMapping("{mapping}")
	public String forwardPage(@PathVariable String mapping) {
		return mapping;
	}
}
