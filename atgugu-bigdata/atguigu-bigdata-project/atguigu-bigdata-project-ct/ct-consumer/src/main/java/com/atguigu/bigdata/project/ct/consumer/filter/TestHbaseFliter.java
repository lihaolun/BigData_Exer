package com.atguigu.bigdata.project.ct.consumer.filter;

import com.atguigu.bigdata.project.ct.common.constant.Names;
import com.atguigu.bigdata.project.ct.common.util.HBaseUtil;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

public class TestHbaseFliter {
    public static void main(String[] args) throws Exception {
        HBaseUtil.start();
        Table table = HBaseUtil.getTable(Names.TABLE_CALLLOG.value());

        String call = "19342117869";
        String start = "20180301";
        String end = "20180501";

        //查询有三种方式，Get、Scan和过滤器
        Scan scan = new Scan();
     /*   //包含开始
        scan.setStartRow(Bytes.toBytes("5_19342117869"));
        //不包含结束
        scan.setStopRow(Bytes.toBytes("5_19342117869"));
        Filter filter = new InclusiveStopFilter(Bytes.toBytes("5_19342117869"));*/

      /*  Filter f1 = new SingleColumnValueFilter(Bytes.toBytes("info"), Bytes.toBytes("call1"), CompareFilter.CompareOp.EQUAL, Bytes.toBytes(call));
        Filter f2 = new SingleColumnValueFilter(Bytes.toBytes("info"), Bytes.toBytes("call2"), CompareFilter.CompareOp.EQUAL, Bytes.toBytes(call));
        Filter f3 = new SingleColumnValueFilter(Bytes.toBytes("info"), Bytes.toBytes("calltime"), CompareFilter.CompareOp.GREATER_OR_EQUAL, Bytes.toBytes(start));
        Filter f4 = new SingleColumnValueFilter(Bytes.toBytes("info"), Bytes.toBytes("calltime"), CompareFilter.CompareOp.LESS_OR_EQUAL, Bytes.toBytes(end));
        FilterList filterList1 = new FilterList(FilterList.Operator.MUST_PASS_ONE);
        filterList1.addFilter(f1);
        filterList1.addFilter(f2);
        FilterList filterList2 = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        filterList2.addFilter(filterList1);
        filterList2.addFilter(f3);
        filterList2.addFilter(f4);
        scan.setFilter(filterList2);
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            for (Cell cell : result.rawCells()) {
                System.out.println(Bytes.toString(CellUtil.cloneRow(cell)));
            }
        }*/

        HBaseUtil.end();

    }
}
