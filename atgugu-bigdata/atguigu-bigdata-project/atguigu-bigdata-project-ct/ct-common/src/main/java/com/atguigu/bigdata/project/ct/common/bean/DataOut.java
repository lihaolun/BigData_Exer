package com.atguigu.bigdata.project.ct.common.bean;

import java.io.Closeable;
import java.io.IOException;

public interface DataOut extends Closeable {
    public void setPath(String path);
    public void write(String string) throws IOException;
    public void write(Object object) throws IOException;

}
