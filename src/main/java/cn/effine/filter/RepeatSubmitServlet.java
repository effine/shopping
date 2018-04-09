/**
 * @Date 2014年11月25日  上午10:10:45
 * @author effine
 */

package cn.effine.filter;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 * @Desc 防止表单重复提交
 */
public class RepeatSubmitServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(RepeatSubmitServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doPost(req, resp);
        } catch (Exception e) {
            logger.error("doPost方法异常：" + e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        int count = 0;
        int divisor = 2;
        try {
            out = resp.getWriter();
            Token token = Token.getInstance();
            if (token.isTokenValid(req)) {
                if (count % divisor == 1) {
                    count = 0;
                } else {
                    count++;
                }
                logger.info("测试：" + count);
                out.println("success");

            } else {
                token.saveToken(req);
                out.println("你已经提交了表单，同一表单不能两次提交");
            }
            out.close();
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
