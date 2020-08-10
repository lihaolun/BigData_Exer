package com.atguigu.bigdata.project.ct.common.util;

import java.text.DecimalFormat;

public class NumberUtil {
    public static String format(int num, int len) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append("0");
        }

        DecimalFormat df = new DecimalFormat(sb.toString());
        String format = df.format(num);
        return format;
    }
}
