package com.atguigu.bigdata.project.ct.common.constant;

import com.atguigu.bigdata.project.ct.common.bean.Val;

public enum Formats implements Val {
    DATE_TIMESTAMP("yyyyMMddHHmmss"),
    DATE_YEARMONTH("yyyyMM");

    private String format;

    Formats(String format) {
        this.format = format;
    }

    @Override
    public String value() {
        return format;
    }
}
