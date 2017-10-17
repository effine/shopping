/**
 * @author effine
 * @Date 2015年12月5日  下午3:31:23
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转
 *
 * @author effine
 * @Date 2017-10-15 20:37
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */
@Controller
public class ForwardController {

    String index = "index";

    /**
     * @return
     */
    @RequestMapping("/")
    public String toIndex() {
        return index;
    }

    /**
     * @param mapping 访问路径
     * @return
     */
    @RequestMapping("{mapping}")
    public String forwardPage(@PathVariable String mapping) {
        return mapping;
    }

}
