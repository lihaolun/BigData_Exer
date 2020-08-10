package com.atguigu.bigdata.common.constant;

public enum Name {

    NAMESPACE_ATGUIGU("atguigu"),
    TABLE_WEIBO("weibo"),
    TABLE_RELATION("relation"),
    TABLE_INBOX("inbox");

    private String name;

    private Name(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
