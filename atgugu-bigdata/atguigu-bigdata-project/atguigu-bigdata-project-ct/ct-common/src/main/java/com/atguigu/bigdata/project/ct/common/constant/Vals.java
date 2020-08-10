package com.atguigu.bigdata.project.ct.common.constant;

import com.atguigu.bigdata.project.ct.common.bean.Val;

public enum Vals implements Val {

    INT_0(0),
    INT_1(1),
    INT_6(6),
    STRING_0("0");

    private Object value;

    private Vals(Object object) {
        this.value = object;
    }

    @Override
    public Object value() {
        return value;
    }

    public int intValue(){
        return (Integer) value;
    }
}
