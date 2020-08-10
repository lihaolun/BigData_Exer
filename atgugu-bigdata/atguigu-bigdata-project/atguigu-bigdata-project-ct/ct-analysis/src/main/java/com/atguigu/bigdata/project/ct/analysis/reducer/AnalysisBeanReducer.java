package com.atguigu.bigdata.project.ct.analysis.reducer;

import com.atguigu.bigdata.project.ct.analysis.bean.OutputKey;
import com.atguigu.bigdata.project.ct.analysis.bean.OutputValue;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AnalysisBeanReducer extends Reducer<OutputKey, Text, OutputKey, OutputValue> {
    @Override
    protected void reduce(OutputKey key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        int sumCount = 0;
        int sunDuration = 0;
        for (Text value : values) {
            sunDuration += Integer.valueOf(value.toString());
            sumCount++;
        }
        context.write(key, new OutputValue(sumCount + "", sunDuration + ""));
    }
}
