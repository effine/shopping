/**
 * @author effine
 * @date 2014年6月11日  下午2:07:22
 */

package cn.effine.lab.email;

import java.util.HashMap;
import java.util.Map;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 * 邮件变量
 */
public class MailConstants {

    /***************** 邮件服务器信息 ******************/
//	public static String SMTP = "smtp.exmail.qq.com"; /*发送邮件使用的smtp(Simple Message Transfer Protocol 简单邮件传输协议)*/
//	public static String SMTP = "smtp.qq.com"; /*发送邮件使用的smtp(Simple Message Transfer Protocol 简单邮件传输协议)*/
    /**
     * 发送邮件使用的smtp(Simple Message Transfer Protocol 简单邮件传输协议)
     */
    public static final String SMTP = "smtp.163.com";

    /**
     * SMTP 主机
     */
    public static final String SMTP_HOST = "mail.smtp.host";
    /**
     * SMTP 验证
     */
    public static final String SMTP_AUTH = "mail.smtp.auth";


    /***************** 发件人信息 ******************/
//	public static String SENDER_EMAIL = "851474818@qq.com"; /* 邮箱 */
//	public static String SENDER_USERNAME = "851474818";	/* 登录用户名 */
//	public static String SENDER_PASSWD = "********";	/* 登录密码 */

    /**
     * 邮箱
     */
    public static final String SENDER_EMAIL = "test@163.com";
    /**
     * 登录用户名
     */
    public static final String SENDER_USERNAME = "effine";
    // TODO getEncryptedPass();
    /**
     * 登录密码
     */
    public static final String SENDER_PASSWD = "";
    /***************** 邮件信息 ******************/
    /**
     * 邮件内容类型
     */
    public static final String MIMETYPE = "text/html;charset=utf-8";
    /**
     * 发送邮件标题
     */
    public static final String TITLE = "email title";
    /**
     * 邮件内容(支持html语法)
     */
    public static final String CONTENT = "email content;<b><a href='#'>test</a></b>";

    /***************** 收件人信息 ******************/
    /**
     * 收件人邮箱
     */
    protected static final String[] RECEIVED_EMAIL = {"654714226@qq.com"};

    /**
     * 邮件附件(key:附件完整路径名；value：附件重新命名)
     */
    protected static final Map<String, String> ANNEX = new HashMap<>();

    static {
        ANNEX.put("D:\\mail\\1.txt", "1.txt");
        ANNEX.put("D:\\mail\\2.txt", "2.txt");
    }

    private MailConstants() {
    }
}
