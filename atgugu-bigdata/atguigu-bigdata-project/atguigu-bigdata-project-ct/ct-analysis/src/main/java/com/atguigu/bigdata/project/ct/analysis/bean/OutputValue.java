package com.atguigu.bigdata.project.ct.analysis.bean;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OutputValue implements Writable {
    private String sumcount;
    private String sumduration;

    public OutputValue() {
    }

    public OutputValue(String sumcount, String sumduration) {
        this.sumcount = sumcount;
        this.sumduration = sumduration;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(sumcount);
        dataOutput.writeUTF(sumduration);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        sumcount = dataInput.readUTF();
        sumduration = dataInput.readUTF();
    }

    public String getSumcount() {
        return sumcount;
    }

    public void setSumcount(String sumcount) {
        this.sumcount = sumcount;
    }

    public String getSumduration() {
        return sumduration;
    }

    public void setSumduration(String sumduration) {
        this.sumduration = sumduration;
    }
}
