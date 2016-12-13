package cn.effine.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * properties工具类
 */
public class PropertiesUtils {

    private static Logger logger = Logger.getLogger(PropertiesUtils.class);

    private PropertiesUtils() {
        // 构造方法私有化，外部不能实例化该类
    }

    private static Properties confProperties;

    static {
        confProperties = new Properties();
        try {
            // 解析文件config.properties
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("conf.properties");
            confProperties.load(is);
            is.close();
        } catch (IOException e) {
            logger.error(e);
        }
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