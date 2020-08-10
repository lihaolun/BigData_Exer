package com.atguigu.bigdata.project.ct.consumer.bean;

import com.atguigu.bigdata.project.ct.common.bean.Consumer;
import com.atguigu.bigdata.project.ct.common.constant.Names;
import com.atguigu.bigdata.project.ct.consumer.dao.HbaseDao;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

public class CalllogConsumer implements Consumer {

    @Override
    public void consumer() {
        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemResourceAsStream("consumer.properties"));
            //创建消费者
            KafkaConsumer kafkaConsumer = new KafkaConsumer(properties);
            //订阅主题
            kafkaConsumer.subscribe(Collections.singletonList(Names.KAFKA_TOPIC_CALLLOG.value()));
            HbaseDao dao = new HbaseDao();
            dao.init();
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record.value());
                    dao.puts(record.value());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
