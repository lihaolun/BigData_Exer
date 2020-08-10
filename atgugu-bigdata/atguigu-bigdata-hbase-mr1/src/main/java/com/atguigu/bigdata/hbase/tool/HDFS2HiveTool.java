package com.atguigu.bigdata.hbase.tool;

import com.atguigu.bigdata.hbase.reducer.InsertDataReducer;
import com.atguigu.bigdata.hbase.mapper.LoadDataMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobStatus;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.Tool;

public class HDFS2HiveTool implements Tool {
    @Override
    public int run(String[] strings) throws Exception {

        Job job = Job.getInstance();
        job.setJarByClass(HDFS2HiveTool.class);
        job.setMapperClass(LoadDataMapper.class);
        job.setMapOutputKeyClass(ImmutableBytesWritable.class);
        job.setMapOutputValueClass(Put.class);
        Path path = new Path("hdfs://hadoop101:9000/input_fruit/fruit.tsv");
        FileInputFormat.addInputPath(job,path);
        TableMapReduceUtil.initTableReducerJob("fruit",InsertDataReducer.class,job);

        return job.waitForCompletion(true) ? JobStatus.State.SUCCEEDED.getValue() : JobStatus.State.FAILED.getValue();
    }

    @Override
    public void setConf(Configuration configuration) {

    }

    @Override
    public Configuration getConf() {
        return null;
    }
}
