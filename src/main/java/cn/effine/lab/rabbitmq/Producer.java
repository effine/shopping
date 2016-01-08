/**
 * @author effine
 * @Date 2016年1月5日  下午6:26:17
 * @email verphen#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.lab.rabbitmq;

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang.SerializationUtils;

/**
 * 队列生产者
 */
public class Producer extends EndPoint {

	public Producer(String queue) throws IOException {
		super(queue);
	}

	public void sendMessage(Serializable message) throws IOException {
		channel.basicPublish("", queue, null, SerializationUtils.serialize(message));
	}
}
