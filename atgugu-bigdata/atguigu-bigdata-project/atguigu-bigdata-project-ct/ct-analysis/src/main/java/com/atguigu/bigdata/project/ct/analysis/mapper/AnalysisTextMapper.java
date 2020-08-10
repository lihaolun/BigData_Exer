package com.atguigu.bigdata.project.ct.analysis.mapper;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class AnalysisTextMapper extends TableMapper<Text, Text> {
    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
        // 5_19342117869_20181025030221_17336673697_6170_1
        String rowKey = Bytes.toString(key.get());
        String[] values = rowKey.split("_");
        String call1 = values[1];
        String calltime = values[2];
        String call2 = values[3];
        String duration = values[4];

        String year = calltime.substring(0, 4);
        String yearmonth = calltime.substring(0, 6);
        String date = calltime.substring(0, 8);

        context.write(new Text(call1 + "_" + year), new Text(duration));
        context.write(new Text(call1 + "_" + yearmonth), new Text(duration));
        context.write(new Text(call1 + "_" + date), new Text(duration));

        context.write(new Text(call2 + "_" + year), new Text(duration));
        context.write(new Text(call2 + "_" + yearmonth), new Text(duration));
        context.write(new Text(call2 + "_" + date), new Text(duration));
    }
}
