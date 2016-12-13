/**
 * @author effine
 * @Date 2015年11月13日  上午12:00:09
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.dao.impl.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

/**
 * DAO包装类
 */
public class DaoSupport {

    private static String configurePath = "mybatis-config.xml";
    private static SqlSessionFactory sqlSessionFactory;
    private static Logger logger = Logger.getLogger(DaoSupport.class);

    private DaoSupport() {
    }

    static {
        try {
            // 方式1
            Reader reader = Resources.getResourceAsReader(configurePath);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

            // 方式2
            // InputStream stream = Resources.getResourceAsStream(configurePath);
            // sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    /*
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
     * @Deprecated (查看代码处理逻辑后删除, 没有必要的代码，不再需要)
     */
    @Deprecated
    public static String showMessages(CRUD_Enum type, int count) {
        switch (type) {
            case Add:
                logger.info("添加了" + count + "条记录。");
                break;
            case Delete:
                logger.info("删除了" + count + "条记录。");
                break;
            case Update:
                logger.info("更新了" + count + "条记录。");
                break;
            case Query:
                logger.info("匹配了" + count + "条记录。");
                break;
            case List:
                logger.info("共有" + count + "条记录。");
                break;
            default:
                break;
        }
        return null;
    }
}
