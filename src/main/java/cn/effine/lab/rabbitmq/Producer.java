/**
 * @author effine
 * @Date 2016年1月5日  下午6:26:17
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.lab.rabbitmq;

import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

/**
 * 队列生产者
 */
public class Producer extends BaseEndPoint {

    public Producer(String queueName) throws IOException {
        super(queueName);
    }

    public void sendMessage(Serializable message) throws IOException {
        channel.basicPublish("", queueName, null, SerializationUtils.serialize(message));
    }
}
