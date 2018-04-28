/**
 * @author effine
 * @Date 2016年1月5日  下午6:28:05
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.lab.rabbitmq;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.*;

public class Main {
    public Main() throws IOException {

        int corePoolSize = 20;
        int maximumPoolSize = 40;
        long keepAliveTime = 3;
        String nameFormat = "thread-pool-d%";
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(50);
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat(nameFormat).build();

        ExecutorService executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue, threadFactory);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    new QueueConsumer("queue");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Producer producer = new Producer("queue");
        int maxNum = 100000;
        for (int i = 0; i < maxNum; i++) {
            HashMap<String, Integer> message = new HashMap<String, Integer>();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message Number " + i + " sent.");
        }
    }
}
