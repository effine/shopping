/**
 * @author effine
 * @Date 2016年1月5日  下午6:26:55
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.lab.rabbitmq;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Map;

/**
 * @author effine
 * @Date 2017-10-15 20:37
 * 队列消费者
 */
public class QueueConsumer extends BaseEndPoint implements Runnable, Consumer {

    private static Logger logger = Logger.getLogger(QueueConsumer.class);

    public QueueConsumer(String queueName) throws IOException {
        super(queueName);
    }

    @Override
    public void run() {
        try {
            // start consuming messages. Auto acknowledge messages.
            channel.basicConsume(queueName, true, this);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    /**
     * Called when consumer is registered.
     */
    @Override
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer " + consumerTag + " registered");
    }

    /**
     * Called when new message is available.
     */
    @Override
    public void handleDelivery(String consumerTag, Envelope env, BasicProperties props, byte[] body) throws IOException {
        @SuppressWarnings("unchecked")
        Map<String, Integer> map = (Map<String, Integer>) SerializationUtils.deserialize(body);
        System.out.println("Message Number " + map.get("message number") + " received.");

    }

    @Override
    public void handleCancel(String consumerTag) {
    }

    @Override
    public void handleCancelOk(String consumerTag) {
    }

    @Override
    public void handleRecoverOk(String consumerTag) {
    }

    @Override
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {
    }
}
