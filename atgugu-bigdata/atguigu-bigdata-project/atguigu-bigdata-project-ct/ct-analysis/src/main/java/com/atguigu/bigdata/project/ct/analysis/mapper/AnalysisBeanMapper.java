package com.atguigu.bigdata.project.ct.analysis.mapper;

import com.atguigu.bigdata.project.ct.analysis.bean.OutputKey;
import com.atguigu.bigdata.project.ct.analysis.bean.OutputValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class AnalysisBeanMapper extends TableMapper<OutputKey, Text> {
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

        context.write(new OutputKey(call1, year), new Text(duration));
        context.write(new OutputKey(call1, yearmonth), new Text(duration));
        context.write(new OutputKey(call1, date), new Text(duration));

        context.write(new OutputKey(call2, year), new Text(duration));
        context.write(new OutputKey(call2, yearmonth), new Text(duration));
        context.write(new OutputKey(call2, date), new Text(duration));
    }
}
