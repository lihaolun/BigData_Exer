package com.atguigu.bigdata.project.ct.consumer;

import com.atguigu.bigdata.project.ct.common.bean.Consumer;
import com.atguigu.bigdata.project.ct.consumer.bean.CalllogConsumer;


public class BootStrap {
    public static void main(String[] args) {
        //创建消费者
        Consumer consumer = new CalllogConsumer();
        //消费数据
        consumer.consumer();
    }

}
