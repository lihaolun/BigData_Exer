package com.atguigu.bigdata.common.constant;

public enum Column {

    COLUMN_FAMILY_INFO("info"),
    COLUMN_FAMILY_ATTENDS("attention"),
    COLUMN_FAMILY_CONTENT("content"),
    COLUMN_FAMILY_FANS("fans");


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Column(String name) {
        this.name = name;
    }
}
