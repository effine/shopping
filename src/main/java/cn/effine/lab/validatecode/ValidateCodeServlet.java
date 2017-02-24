package cn.effine.lab.validatecode;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 验证码
 */
public class ValidateCodeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(ValidateCodeServlet.class);

    @Override
    protected void doGet(HttpServletRequest reqeust,
                         HttpServletResponse response) throws ServletException, IOException {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        HttpSession session = reqeust.getSession();

        ValidateCode vCode = new ValidateCode(120, 40, 5, 100);
        session.setAttribute("code", vCode.getCode());
        try {
            vCode.write(response.getOutputStream());
        } catch (Exception e) {
            logger.error(e);
        }
    }
}