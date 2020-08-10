package com.atguigu.bigdata.kafka.producer;

import java.util.Properties;

public class CustomerProducer {
    public static void main(String[] args) {
        //创建配置对象
        Properties properties = new Properties();
        //kafka集群配置
        properties.put("bootstrap.servers","hadoop101:9092");


    }
}
