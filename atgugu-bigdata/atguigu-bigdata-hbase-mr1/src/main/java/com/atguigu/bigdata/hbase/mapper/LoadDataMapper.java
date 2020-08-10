package com.atguigu.bigdata.hbase.mapper;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class LoadDataMapper extends Mapper<LongWritable,Text,ImmutableBytesWritable,Put>{
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取一行
        String line = value.toString();
        //分割
        String[] fields = line.split("\t");
        ImmutableBytesWritable rowKey = new ImmutableBytesWritable(Bytes.toBytes(fields[0]));
        Put put = new Put(Bytes.toBytes(fields[0]));
        byte[] family = Bytes.toBytes("info");
        byte[] columnName = Bytes.toBytes("name");
        byte[] columnColor = Bytes.toBytes("color");
        byte[] nameVal = Bytes.toBytes(fields[1]);
        byte[] colorVal = Bytes.toBytes(fields[2]);
        put.addColumn(family,columnName,nameVal);
        put.addColumn(family,columnColor,colorVal);

        context.write(rowKey,put);
    }
}
