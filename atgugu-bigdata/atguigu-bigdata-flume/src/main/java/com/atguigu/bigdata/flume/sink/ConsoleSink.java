package com.atguigu.bigdata.flume.sink;

import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;

/**
 * 输出数据到控制台
 */
public class ConsoleSink extends AbstractSink implements Configurable {
    /**
     * 执行操作
     * @return
     * @throws EventDeliveryException
     */
    public Status process() throws EventDeliveryException {

        System.out.println("ConsoleSink....process...");

        Status result = Status.READY;
        // 获取Channle的对象
        Channel channel = getChannel();
        // 获取channel中的事务对象
        Transaction transaction = channel.getTransaction();
        Event event = null;

        try {
            // 事务开启
            transaction.begin();
            event = channel.take();

            if (event != null) {
                System.out.println("Event: " + new String(event.getBody(), "UTF-8"));
            } else {
                // No event found, request back-off semantics from the sink runner
                result = Status.BACKOFF;
            }
            // 事务提交
            transaction.commit();
        } catch (Exception ex) {
            // 事务回滚
            transaction.rollback();
            throw new EventDeliveryException("Failed to log event: " + event, ex);
        } finally {
            // 事务关闭
            transaction.close();
        }

        // 返回状态对象
        return result;
    }

    public void configure(Context context) {

    }
}
