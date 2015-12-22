/**
 * @author verphen
 * @date 2014年6月11日  下午1:44:20
 */

package cn.effine.lab.email;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang.StringUtils;

public class MailUtils {

	public static void main(String[] args) {
		try {
			sendmail(MailConstants.TITLE, MailConstants.SENDER_EMAIL,
					MailConstants.RECEIVED_EMAIL, MailConstants.CONTENT,
					MailConstants.ANNEX, MailConstants.MIMETYPE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  
	/**
	 * 发送邮件方法
	 * 
	 * @param title
	 *            邮件标题
	 * @param sender_email
	 *            发件人
	 * @param received_email
	 *            收件人
	 * @param content
	 *            邮件内容
	 * @param annex
	 *            邮件附件
	 * @param mimeType
	 *            mimeType
	 * @throws Exception
	 *             异常信息
	 */
	@SuppressWarnings({ "static-access"})
	public static void sendmail(String title, String sender_email,
			String[] received_email, String content, Map<String, String> annex,
			String mimeType) throws Exception {

		Properties props = new Properties();
		props = java.lang.System.getProperties(); // 获得系统属性对象
		props.setProperty(MailConstants.SMTP_HOST, MailConstants.SMTP); // 设置SMTP主机
		props.setProperty(MailConstants.SMTP_AUTH, "true"); // 是否到服务器用户名和密码验证
		
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		
		
		// 到服务器验证发送的用户名和密码是否正确
		AutherticatorBean myEmailAuther = new AutherticatorBean(
				MailConstants.SENDER_USERNAME, MailConstants.SENDER_PASSWD);
		// 设置邮件会话
		Session mailSession = javax.mail.Session.getInstance(props,
				(Authenticator) myEmailAuther);
		// 设置传输协议
		javax.mail.Transport transport = mailSession.getTransport("smtp");
		// 设置from、to等信息
		MimeMessage	mimeMsg = new javax.mail.internet.MimeMessage(mailSession);
		if (!StringUtils.isEmpty(sender_email)) {
			InternetAddress sentFrom = new InternetAddress(sender_email);
			mimeMsg.setFrom(sentFrom); // 设置发送人地址
		}

		InternetAddress[] sendTo = new InternetAddress[received_email.length];
		for (int i = 0; i < received_email.length; i++) {
			sendTo[i] = new InternetAddress(received_email[i]);
		}

		mimeMsg.setRecipients(javax.mail.internet.MimeMessage.RecipientType.TO,
				sendTo);
		mimeMsg.setSubject(title, "utf-8");

		MimeBodyPart messageBodyPart1 = new MimeBodyPart();
		messageBodyPart1.setContent(content, mimeType);

		Multipart multipart = new MimeMultipart();// 附件传输格式
		multipart.addBodyPart(messageBodyPart1);

		for (Map.Entry<String, String> entry : annex.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			if ("".equalsIgnoreCase(value) || null == value) {
				value = key.substring(key.lastIndexOf("\\") + 1);
			}

			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(key); /* 得到数据源 */
			messageBodyPart2.setDataHandler(new DataHandler(fds)); /* 得到附件本身并至入BodyPart */
			messageBodyPart2.setFileName(MimeUtility.encodeText(value)); /* 得到文件名同样至入BodyPart */
			multipart.addBodyPart(messageBodyPart2);
		}

		mimeMsg.setContent(multipart);
		mimeMsg.setSentDate(new Date()); /* 设置信件头的发送日期 */
		mimeMsg.saveChanges();
		transport.send(mimeMsg); /* 发送邮件 */
		System.out.println("邮件发送成功");
		transport.close();
	}
}
