/**
 * @Date 2014年11月25日  上午10:15:55
 * @author effine
 * @email iballader@gmail.com
 */

package cn.effine.filter;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Token操作类
 */
public class Token {

    static final String TOKEN_KEY = "ltai701";
    private static Logger logger = Logger.getLogger(Token.class);
    private static Token instance = new Token();

    /**
     * 最近一次生成令牌值的时间戳
     */
    private long previousTime;

    /**
     * getInstance()方法得到单例类实例
     */
    public static Token getInstance() {
        return instance;
    }

    /**
     * 判断请求参数中的令牌值是否有效
     *
     * @param request
     * @return
     */
    public synchronized boolean isTokenValid(HttpServletRequest request) {
        //得到请求的当前session对象
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }

        //从session中取出保存的令牌值
        String saved = (String) session.getAttribute(TOKEN_KEY);
        if (saved == null) {
            return false;
        }

        //清除session中的令牌值
        resetToken(request);

        //得到请求参数中的令牌值
        String token = request.getParameter(TOKEN_KEY);
        if (token == null) {
            return false;
        }

        return saved.equals(token);
    }

    /**
     * 清除session中的令牌值
     *
     * @param request
     * @return
     */
    public synchronized void resetToken(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(TOKEN_KEY);
    }

    /**
     * 产生一个新的令牌值 ，保存到session中
     * 如果当前sesison不存在，则创建一个新的的session
     *
     * @param request
     * @return
     */
    public synchronized void saveToken(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String token = generateToken(request);
        if (token != null) {
            session.setAttribute(TOKEN_KEY, token);
        }
    }

    /**
     * 根据用户会话id和当前系统时间生成一个唯一的令牌
     *
     * @param request
     * @return
     */
    public synchronized String generateToken(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        try {
            byte[] id = session.getId().getBytes();
            long currentTime = System.currentTimeMillis();
            if (currentTime == previousTime) {
                currentTime++;

            }
            previousTime = currentTime;
            byte[] now = String.valueOf(currentTime).getBytes();
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(id);
            md.update(now);
            return toHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
            return null;
        }
    }

    /**
     * 将一个字节数组转换一个十六进制数字的字符串
     *
     * @param buffer
     * @return
     */
    private String toHex(byte[] buffer) {
        StringBuilder sb = new StringBuilder(buffer.length * 2);
        for (int i = 0; i < buffer.length; i++) {
            sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
            sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
        }
        return sb.toString();
    }

    /**
     * 从Session中得到令牌值，如果Session中没有令牌值 ，则生成一个新的令牌值
     *
     * @param request
     * @return
     */
    public synchronized String getToken(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (null == session) {
            return null;
        }

        String token = (String) session.getAttribute(TOKEN_KEY);

        if (null == token) {

            token = generateToken(request);
            if (token != null) {
                session.setAttribute(TOKEN_KEY, token);
                return token;
            } else {
                return null;
            }
        } else {
            return token;
        }
    }
}


