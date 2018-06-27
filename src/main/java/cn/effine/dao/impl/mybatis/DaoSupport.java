/**
 * @author effine
 * @Date 2015年11月13日  上午12:00:09
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.dao.impl.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * DAO包装类
 *
 * @author effine
 * @Date 2017-10-15 20:37
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */
public class DaoSupport {

    private static String configurePath = "mybatis-config.xml";
    private static SqlSessionFactory sqlSessionFactory;
    private static SqlSessionFactory sqlSessionFactory2;
    private static final Logger logger = LoggerFactory.getLogger(DaoSupport.class);

    static {
        try {
            // 方式1
            Reader reader = Resources.getResourceAsReader(configurePath);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

            // 方式2
            InputStream stream = Resources.getResourceAsStream(configurePath);
            sqlSessionFactory2 = new SqlSessionFactoryBuilder().build(stream);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 获取数据库访问会话session
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    /**
     * 返回操作记录消息
     *
     * @param type  类型
     * @param count 统计数量
     * @return
     * @Deprecated (查看代码处理逻辑后删除, 没有必要的代码 ， 不再需要)
     */
    @Deprecated
    public static String showMessages(CrudEnum type, int count) {
        switch (type) {
            case ADD:
                logger.info("添加了" + count + "条记录。");
                break;
            case DELETE:
                logger.info("删除了" + count + "条记录。");
                break;
            case UPDATE:
                logger.info("更新了" + count + "条记录。");
                break;
            case QUERY:
                logger.info("匹配了" + count + "条记录。");
                break;
            case LIST:
                logger.info("共有" + count + "条记录。");
                break;
            default:
                break;
        }
        return null;
    }
}
