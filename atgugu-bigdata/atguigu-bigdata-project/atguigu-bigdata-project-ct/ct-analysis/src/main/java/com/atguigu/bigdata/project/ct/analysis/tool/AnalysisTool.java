package com.atguigu.bigdata.project.ct.analysis.tool;

import com.atguigu.bigdata.project.ct.analysis.io.MySQLOutputFormat;
import com.atguigu.bigdata.project.ct.analysis.mapper.AnalysisTextMapper;
import com.atguigu.bigdata.project.ct.analysis.reducer.AnalysisTextReducer;
import com.atguigu.bigdata.project.ct.common.constant.Names;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobStatus;
import org.apache.hadoop.util.Tool;

public class AnalysisTool implements Tool {
    @Override
    public int run(String[] strings) throws Exception {
        Job job = Job.getInstance();
        job.setJarByClass(AnalysisTool.class);
        Scan scan = new Scan();
        scan.addFamily(Bytes.toBytes(Names.TABLE_FAMILY_INFO.value()));
        TableMapReduceUtil.initTableMapperJob(Names.TABLE_CALLLOG.value(),scan,AnalysisTextMapper.class,Text.class,Text.class,job);

        job.setReducerClass(AnalysisTextReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setOutputFormatClass(MySQLOutputFormat.class);
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
