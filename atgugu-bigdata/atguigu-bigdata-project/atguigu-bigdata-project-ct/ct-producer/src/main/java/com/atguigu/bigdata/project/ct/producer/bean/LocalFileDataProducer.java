package com.atguigu.bigdata.project.ct.producer.bean;

import com.atguigu.bigdata.project.ct.common.bean.DataIn;
import com.atguigu.bigdata.project.ct.common.bean.DataOut;
import com.atguigu.bigdata.project.ct.common.bean.Producer;
import com.atguigu.bigdata.project.ct.common.constant.Formats;
import com.atguigu.bigdata.project.ct.common.util.DateUtil;
import com.atguigu.bigdata.project.ct.common.util.NumberUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class LocalFileDataProducer implements Producer {
    private DataIn in;
    private DataOut out;

    @Override
    public void setIn(DataIn in) {
        this.in = in;
    }

    @Override
    public void setOut(DataOut out) {
        this.out = out;
    }

    /**
     * 生产数据
     */
    @Override
    public void producer() {
        try {
            //获取两个通讯录数据
            List<Contact> contactList = in.read(Contact.class);
            in.close();
            in = null;
            while (true) {
                int call1Index = new Random().nextInt(contactList.size());
                int call2Index = -1;
                while (true) {
                    call2Index = new Random().nextInt(contactList.size());
                    if (call1Index != call2Index) {
                        break;
                    }
                }
                Contact call1 = contactList.get(call1Index);
                Contact call2 = contactList.get(call2Index);

                //随机生成通话时间
                String startTimeString = "20180101000000";
                String stopTimeString = "20190101000000";

                long startTime = DateUtil.parse(startTimeString, Formats.DATE_TIMESTAMP).getTime();
                long stopTime = DateUtil.parse(stopTimeString, Formats.DATE_TIMESTAMP).getTime();

                long callTime = startTime + (long) ((stopTime - startTime) * Math.random());
                String callTimeString = DateUtil.format(new Date(callTime), Formats.DATE_TIMESTAMP);
                //通话时长
                int callDuration = new Random().nextInt(9999);
                String callDurationString = NumberUtil.format(callDuration, 4);
                //形成通话记录
                CallLog callLog = new CallLog(call1.getTel(), call2.getTel(), callTimeString, callDurationString);
                //将通话记录输入到指定日志文件中
                System.out.println(callLog);
                out.write(callLog);

                Thread.sleep(300);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            out.close();
            out = null;
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void close() throws Exception {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.close();
        }
    }
}
