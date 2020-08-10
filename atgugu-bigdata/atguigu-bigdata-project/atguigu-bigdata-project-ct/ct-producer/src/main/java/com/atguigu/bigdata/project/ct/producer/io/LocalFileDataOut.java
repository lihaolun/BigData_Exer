package com.atguigu.bigdata.project.ct.producer.io;


import com.atguigu.bigdata.project.ct.common.bean.DataOut;

import java.io.*;


public class LocalFileDataOut implements DataOut {

    private PrintWriter writer = null;

    public LocalFileDataOut(String path) {
        setPath(path);
    }

    @Override
    public void setPath(String path) {
        try {
            this.writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path)));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void write(Object object) throws IOException {
        write(object.toString());
    }

    public void write(String string) throws IOException {
        writer.println(string);
        writer.flush();
    }

    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }
}
