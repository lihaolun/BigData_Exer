package com.atguigu.bigdata.project.ct.consumer.filter;

import com.atguigu.bigdata.project.ct.common.util.HBaseUtil;

import java.util.List;

public class TestStartStopRows {
    public static void main(String[] args) {
        String call = "17336673697";
        String start = "201803";
        String end = "201805";
        List<String[]> strings = HBaseUtil.getStartStopRows(call, start, end);
        for (String[] string : strings) {
            System.out.println(string[0]+"~"+string[1]);
        }
    }
}
