package com.atguigu.bigdata.project.ct.analysis.reducer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AnalysisTextReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        int sumCount = 0;
        int sunDuration = 0;
        for (Text value : values) {
            sunDuration += Integer.valueOf(value.toString());
            sumCount++;
        }
        context.write(key, new Text(sumCount + "_" + sunDuration));
    }
}
