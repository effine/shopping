/**
 * @author effine
 * @date 2015年1月31日  下午4:53:57
 */

package cn.effine.lab.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

import java.util.Set;

public class TestJedis {

    private static final Logger logger = LoggerFactory.getLogger(TestJedis.class);
    private static String extranetHost = "127.0.0.1";
    private static int extranetPort = 6379;

    // 测试
    @Deprecated
    public static void main(String[] args) {
        TestJedis test = new TestJedis();
        logger.info("----------开始调用jedis基本使用方法");
        test.baseJedis();
        logger.info("----------开始调用jedis使用池方法");
        test.poolJedis();
        logger.info("----------全部方法调用完成");
    }

    public static Jedis getRedis() {
        JedisShardInfo info = new JedisShardInfo(extranetHost, extranetPort);
        return new Jedis(info);
    }

    /**
     * jedis的基本使用（jedis非线程安全）
     */
    public void baseJedis() {
        Jedis j = TestJedis.getRedis();
        j.set("hello", "world");
        String output = j.get("hello");
        System.out.println("jedis基本使用: " + output);
    }

    /**
     * jedis使用池
     */
    public void poolJedis() {
        @SuppressWarnings("resource")
        JedisPool pool = new JedisPool(new JedisPoolConfig(), extranetHost);
        Jedis jedis = pool.getResource();
        jedis.auth("yunlu123");
        try {
            // 开始使用
            jedis.set("foo", "bar");
            String foobar = jedis.get("foo");
            System.out.println("jedis使用池：" + foobar);
            jedis.zadd("sose", 0, "car");
            jedis.zadd("sose", 0, "bike");
            Set<String> sose = jedis.zrange("sose", 0, -1);
        } finally {
            if (null != jedis) {
                // 使用完后，将连接放回连接池
                jedis.close();
            }
        }

        // 应用退出时，关闭连接池
        pool.destroy();
    }
}