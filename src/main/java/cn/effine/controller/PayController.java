/**
 * @author effine
 * @Date 2015年10月19日  上午10:08:48
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 * 支付
 */
@RestController
@RequestMapping("pay")
public class PayController {

    /**
     * 支付宝支付
     *
     * @return
     */
    @RequestMapping("alipay")
    public String alipay() {
        return null;
    }

    /**
     * 微信支付
     *
     * @return
     */
    @RequestMapping("wechat")
    public String wechatPay() {
        return null;
    }
}
