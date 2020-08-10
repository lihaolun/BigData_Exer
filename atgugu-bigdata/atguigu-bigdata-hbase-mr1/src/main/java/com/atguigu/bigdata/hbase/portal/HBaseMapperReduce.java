package com.atguigu.bigdata.hbase.portal;

import com.atguigu.bigdata.hbase.tool.HDFS2HiveTool;
import org.apache.hadoop.util.ToolRunner;

public class HBaseMapperReduce {
    public static void main(String[] args) throws Exception {
        ToolRunner.run(new HDFS2HiveTool(),args);
    }
}
