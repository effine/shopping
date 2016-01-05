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
 * The producer endpoint that writes to the queue.
 *
 */
public class Producer extends EndPoint {

	public Producer(String endPointName) throws IOException {
		super(endPointName);
	}

	public void sendMessage(Serializable object) throws IOException {
		channel.basicPublish("", endPointName, null,
				SerializationUtils.serialize(object));
	}
}
