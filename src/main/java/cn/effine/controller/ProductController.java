package cn.effine.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author effine
 * @Date 2017-07-09 21:21
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 * @sine 0.1
 */

@RestController
public class ProductController {

    @RequestMapping("say")
    public String sayHello() {
        return "hi, product";
    }
}
