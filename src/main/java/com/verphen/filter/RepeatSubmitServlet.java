/**
 * @Date 2014年11月25日  上午10:10:45
 * @author 张亚飞
 */

package com.verphen.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Desc	防止表单重复提交
 */
public class RepeatSubmitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	int count=0;   
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();

		Token token = Token.getInstance();
		if (token.isTokenValid(req)) {
			System.out.println("submit:" + count);
			if (count % 2 == 1)
				count = 0;
			else
				count++;

			out.println("success");

		} else {
			token.saveToken(req);
			out.println("你已经提交了表单，同一表单不能两次提交");
		}
		out.close();
	}
}
