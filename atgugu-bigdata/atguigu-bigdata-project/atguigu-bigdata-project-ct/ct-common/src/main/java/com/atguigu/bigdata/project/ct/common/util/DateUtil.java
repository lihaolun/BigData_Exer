package com.atguigu.bigdata.project.ct.common.util;

import com.atguigu.bigdata.project.ct.common.constant.Formats;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {
    /**
     * 将指定日期按照指定的格式转换为字符串
     * @param d
     * @param f
     * @return
     */
    public static String format(Date d, Formats f) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat(f.value());
        return sdf.format(d);
    }

    /**
     * 将指定的字符串按照指定的格式转换为日期
     * @param date
     * @param f
     * @return
     * @throws Exception
     */
    public static Date parse(String date,Formats f) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat(f.value());
        return sdf.parse(date);
    }

}
