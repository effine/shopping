package cn.effine.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 * properties工具类
 */
public class PropertiesUtils {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
    private static Properties confProperties;

    static {
        confProperties = new Properties();
        try {
            // 解析文件config.properties
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("conf.properties");
            confProperties.load(is);
            is.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private PropertiesUtils() {
        // 构造方法私有化，外部不能实例化该类
    }

    /**
     * 获取properties文件属性
     *
     * @param key conf.properties文件key
     * @return conf.properties文件key对应value
     */
    public static String getProp(String key) {
        return confProperties.getProperty(key);
    }
}