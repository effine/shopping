package cn.effine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author effine
 * @Date 2017-07-09 21:19
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 * @sine 0.1
 */
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan(value = "cn.effine")
@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        logger.info("project start success ...");
    }
}
