/**
 * @author effine
 * @Date 2016年1月5日  下午6:22:20
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.lab.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public abstract class EndPoint {
	protected Channel channel;
	protected Connection connection;
	protected String endPointName;

	public EndPoint(String endpointName) throws IOException {
		this.endPointName = endpointName;

		// Create a connection factory
		ConnectionFactory factory = new ConnectionFactory();

		// 配置rabbitmq服务的连接信息(只需配置主机即可)
		factory.setHost("localhost");

		try {
			connection = factory.newConnection();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}

		// creating a channel
		channel = connection.createChannel();

		// declaring a queue for this channel. If queue does not exist,
		// it will be created on the server.
		channel.queueDeclare(endpointName, false, false, false, null);
	}
}
