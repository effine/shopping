/**
 * @author effine
 * @date 2015年3月25日  下午10:19:27
 */

package cn.effine.utils;

import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

public class EmailUtils {

	private static Logger logger = Logger.getLogger(EmailUtils.class);

	@Deprecated
	public static void main(String[] args) {
		String subject = "面试邀请"; // 主题
		HtmlEmail email = new HtmlEmail();
		email.setHostName("mail.effine.cn");	//域名不可用，可以试试主机IP
		email.setSubject(subject); // 设置邮件主题
		email.setAuthentication("noreplay@mail.effine.cn", "Yunlu201*");// SMTP服务器认证,设置帐号、密码
		email.setCharset("utf-8"); // 设置邮件字符集
		try {
			email.addTo("test@163.com", "effine"); // 设置收件人帐号和收件人
			email.setFrom("noreplay@mail.effine.cn", "showname"); // 设置发信的邮件帐号和发信人
			email.setHtmlMsg("邮件内容 email content"); // 设置邮件正文，此方法这里的样式可以显示出来
			email.send();
			System.err.println("邮件发送成功");
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
