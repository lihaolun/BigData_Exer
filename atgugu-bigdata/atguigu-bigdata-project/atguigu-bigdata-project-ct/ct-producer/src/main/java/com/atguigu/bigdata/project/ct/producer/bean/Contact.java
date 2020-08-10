package com.atguigu.bigdata.project.ct.producer.bean;

import com.atguigu.bigdata.project.ct.common.bean.Data;

public class Contact extends Data {

    private String tel;
    private String username;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setContent(String content) {
        String[] contents = content.split("\t");
        tel = contents[0];
        username = contents[1];
    }
}
