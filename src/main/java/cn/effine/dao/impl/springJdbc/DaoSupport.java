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

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class DaoSupport {

    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-jdbc.xml");

    public static JdbcTemplate getJdbcTemplate() {
        return (JdbcTemplate) ctx.getBean("jdbcTemplate");
    }


    public static void main(String[] args) {
        Double num = 1.0;
        DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(4);
        formater.setRoundingMode(RoundingMode.UP);

        System.out.println(Double.valueOf(formater.format(num / 6)));
    }

}
