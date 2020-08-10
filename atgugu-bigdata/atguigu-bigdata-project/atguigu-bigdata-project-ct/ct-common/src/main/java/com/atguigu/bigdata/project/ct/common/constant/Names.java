package com.atguigu.bigdata.project.ct.common.constant;

import com.atguigu.bigdata.project.ct.common.bean.Val;

public enum Names implements Val {
    KAFKA_TOPIC_CALLLOG("calllog"),
    NAMESPACE_CT("ct"),
    TABLE_CALLLOG("calllog"),
    TABLE_FAMILY_INFO("info"),
    TABLE_FAMILY_UNACTIVE("unactive");

    private String value;

    private Names(String name) {
        this.value = name;
    }

    @Override
    public String value() {
        return value;
    }
}
