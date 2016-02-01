/**
 * @author effine
 * @Date 2015年11月15日  下午8:57:34
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.dao.impl.springJdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class DaoSupport {

	private static ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-jdbcTemplate.xml");

	public static JdbcTemplate getJdbcTemplate() {
		return (JdbcTemplate) ctx.getBean("jdbcTemplate");
	}
	
}
