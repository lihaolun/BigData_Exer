package com.atguigu.bigdata.project.ct.analysis.bean;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OutputKey implements WritableComparable<OutputKey> {
    private String call;
    private String date;

    @Override
    public int compareTo(OutputKey o) {
        int result = call.compareTo(o.call);
        if (result==0){
            result = date.compareTo(o.date);
            return result;
        }
        return result;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(call);
        dataOutput.writeUTF(date);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        call = dataInput.readUTF();
        date = dataInput.readUTF();
    }


    public OutputKey() {
    }

    public OutputKey(String call, String date) {
        this.call = call;
        this.date = date;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
