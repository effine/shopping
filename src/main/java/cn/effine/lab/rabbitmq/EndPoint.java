/**
 * @author effine
 * @Date 2016年1月5日  下午6:22:20
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.lab.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.log4j.Logger;

public abstract class EndPoint {

	protected Channel channel;
	protected Connection connection;
	protected String queueName;
	private static Logger logger = Logger.getLogger(EndPoint.class);

	public EndPoint(String queueName) throws IOException {
		this.queueName = queueName;

		ConnectionFactory factory = new ConnectionFactory();
		// 配置rabbitmq服务的连接信息(只需配置主机即可)
		factory.setHost("localhost");
		try {
			connection = factory.newConnection();
		} catch (TimeoutException e) {
			logger.error(e);
		}

		channel = connection.createChannel();
		channel.queueDeclare(queueName, false, false, false, null);
	}
}
