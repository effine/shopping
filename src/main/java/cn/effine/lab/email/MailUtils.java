/**
 * @author effine
 * @date 2014年6月11日  下午1:44:20
 */

package cn.effine.lab.email;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 * 邮件工具类
 */
public class MailUtils {

    private static Logger logger = Logger.getLogger(MailUtils.class);

    private MailUtils() {
    }

    /**
     * 发送邮件方法
     *
     * @param title         邮件标题
     * @param senderEmail   发件人
     * @param receivedEmail 收件人
     * @param content       邮件内容
     * @param annex         邮件附件
     * @param mimeType      mimeType
     * @throws Exception 异常信息
     */
    @SuppressWarnings({"static-access"})
    public static void sendmail(String title, String senderEmail,
                                String[] receivedEmail, String content, Map<String, String> annex,
                                String mimeType) throws Exception {

        Properties props = new Properties();
        // 获得系统属性对象
        props = java.lang.System.getProperties();
        // 设置SMTP主机
        props.setProperty(MailConstants.SMTP_HOST, MailConstants.SMTP);
        // 是否到服务器用户名和密码验证
        props.setProperty(MailConstants.SMTP_AUTH, "true");

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
        MimeMessage mimeMsg = new javax.mail.internet.MimeMessage(mailSession);
        if (!StringUtils.isEmpty(senderEmail)) {
            InternetAddress sentFrom = new InternetAddress(senderEmail);
            // 设置发送人地址
            mimeMsg.setFrom(sentFrom);
        }

        InternetAddress[] sendTo = new InternetAddress[receivedEmail.length];
        for (int i = 0; i < receivedEmail.length; i++) {
            sendTo[i] = new InternetAddress(receivedEmail[i]);
        }

        mimeMsg.setRecipients(javax.mail.internet.MimeMessage.RecipientType.TO,
                sendTo);
        mimeMsg.setSubject(title, "utf-8");

        MimeBodyPart messageBodyPart1 = new MimeBodyPart();
        messageBodyPart1.setContent(content, mimeType);

        // 附件传输格式
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart1);

        for (Map.Entry<String, String> entry : annex.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if ("".equalsIgnoreCase(value) || null == value) {
                value = key.substring(key.lastIndexOf("\\") + 1);
            }

            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            // 得到数据源
            FileDataSource fds = new FileDataSource(key);
            // 得到附件本身并至入BodyPart
            messageBodyPart2.setDataHandler(new DataHandler(fds));
            // 得到文件名同样至入BodyPart
            messageBodyPart2.setFileName(MimeUtility.encodeText(value));
            multipart.addBodyPart(messageBodyPart2);
        }

        mimeMsg.setContent(multipart);
        // 设置信件头的发送日期
        mimeMsg.setSentDate(new Date());
        mimeMsg.saveChanges();
        // 发送邮件
        Transport.send(mimeMsg);
        logger.info("邮件发送成功");
        transport.close();
    }
}
