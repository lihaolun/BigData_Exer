package com.atguigu.bigdata.hbase.tool;

import com.atguigu.bigdata.hbase.reducer.InsertDataReducer;
import com.atguigu.bigdata.hbase.mapper.GetDataMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobStatus;
import org.apache.hadoop.util.Tool;

public class TableToTableTool implements Tool {

    @Override
    public int run(String[] strings) throws Exception {
        //创建脚本对象
        Job job = Job.getInstance();
        job.setJarByClass(TableToTableTool.class);

        Scan scan = new Scan();
        //mapper和reducer
        //hbase对job的配置进行了封装
        TableMapReduceUtil.initTableMapperJob("fruit",scan,GetDataMapper.class,ImmutableBytesWritable.class,Put.class,job);

        TableMapReduceUtil.initTableReducerJob("fruit_mr",InsertDataReducer.class,job);

        return job.waitForCompletion(true)?JobStatus.State.SUCCEEDED.getValue():JobStatus.State.FAILED.getValue();
    }

    @Override
    public void setConf(Configuration configuration) {

    }

    @Override
    public Configuration getConf() {
        return null;
    }

}
