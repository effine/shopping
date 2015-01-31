/**
 * @author verphen
 * @date 2015年1月31日  下午4:53:57
 */

package com.effine.redis;

import redis.clients.jedis.Jedis;

public class TestRedis {
	static String constr = "192.168.168.165";

	public static Jedis getRedis() {
		Jedis jedis = new Jedis(constr);
		return jedis;
	}

	public static void main(String[] args) {
		Jedis j = TestRedis.getRedis();
		String output;
		j.set("hello", "world");
		output = j.get("hello");
		System.out.println(output);

	}
}
