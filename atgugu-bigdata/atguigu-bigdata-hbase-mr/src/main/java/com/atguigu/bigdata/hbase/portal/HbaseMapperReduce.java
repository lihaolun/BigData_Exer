package com.atguigu.bigdata.hbase.portal;

import com.atguigu.bigdata.hbase.tool.TableToTableTool;

import org.apache.hadoop.util.ToolRunner;

public class HbaseMapperReduce {
    public static void main(String[] args) throws Exception {
        ToolRunner.run(new TableToTableTool(),args);
    }
}
