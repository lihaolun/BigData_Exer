package com.atguigu.bigdata.project.ct.consumer.dao;

import com.atguigu.bigdata.project.ct.common.constant.Names;
import com.atguigu.bigdata.project.ct.common.constant.Vals;
import com.atguigu.bigdata.project.ct.common.util.HBaseUtil;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.List;

public class HbaseDao {
    private List<Put> puts = new ArrayList<>();
    private int batchSize = 20;

    /**
     * 初始化
     */
    public void init() {

        try {
            HBaseUtil.start();
            HBaseUtil.createNamespaceNX(Names.NAMESPACE_CT.value());
            HBaseUtil.createTableXX(Names.TABLE_CALLLOG.value(), Vals.INT_6.intValue(), "com.atguigu.bigdata.project.ct.consumer.coprocesser.InsertUnActiveDataCoprocesser");
            HBaseUtil.end();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 向Hbase里导入数据
     *
     * @param value
     */
    public void puts(String value) {

        try {
            //call1 + "\t" + call2 + "\t" + callTime + "\t" + duration
            String[] values = value.split("\t");
            String call1 = values[0];
            String call2 = values[1];
            String callTime = values[2];
            String duration = values[3];
            //预分区
            //rowKey设计
            //分区号
            String regionNum = HBaseUtil.genRegionNum(call1, callTime);
            String rowKey = regionNum + "_" + call1 + "_" + callTime + "_" + call2 + "_" + duration + "_1";
            Put put = new Put(Bytes.toBytes(rowKey));

            put.addColumn(Bytes.toBytes(Names.TABLE_FAMILY_INFO.value()), Bytes.toBytes("call1"), Bytes.toBytes(call1));
            put.addColumn(Bytes.toBytes(Names.TABLE_FAMILY_INFO.value()), Bytes.toBytes("call2"), Bytes.toBytes(call2));
            put.addColumn(Bytes.toBytes(Names.TABLE_FAMILY_INFO.value()), Bytes.toBytes("calltime"), Bytes.toBytes(callTime));
            put.addColumn(Bytes.toBytes(Names.TABLE_FAMILY_INFO.value()), Bytes.toBytes("duration"), Bytes.toBytes(duration));
            put.addColumn(Bytes.toBytes(Names.TABLE_FAMILY_INFO.value()), Bytes.toBytes("flag"), Bytes.toBytes("1"));

            puts.add(put);
            if (puts.size() >= batchSize) {
                HBaseUtil.start();
                Table table = HBaseUtil.getTable(Names.TABLE_CALLLOG.value());
                table.put(puts);
                table.close();
                HBaseUtil.end();
                puts.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
