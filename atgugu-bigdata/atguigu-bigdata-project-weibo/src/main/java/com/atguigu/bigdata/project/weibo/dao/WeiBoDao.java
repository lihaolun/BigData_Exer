package com.atguigu.bigdata.project.weibo.dao;

import com.atguigu.bigdata.common.constant.Column;
import com.atguigu.bigdata.common.constant.Name;
import com.atguigu.bigdata.common.util.HBaseUtil;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeiBoDao {
    /**
     * 发布微博向weibo中增加数据
     *
     * @param userid
     * @param content
     * @param timestamp
     */
    public void addData(String userid, String content, long timestamp) {
        byte[] rowKey = Bytes.toBytes(userid + "_" + (Long.MAX_VALUE - timestamp));
        byte[] columnFamily = Bytes.toBytes(Column.COLUMN_FAMILY_INFO.getName());
        byte[] column = Bytes.toBytes(Column.COLUMN_FAMILY_CONTENT.getName());
        byte[] val = Bytes.toBytes(content);
        Put put = new Put(rowKey);
        put.addColumn(columnFamily, column, val);

        try {
            HBaseUtil.addData(Name.TABLE_WEIBO.getName(), put);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询明星发布的微博数据（5条最近的）
     * @param starid
     */
    public List<String> scanWeiBoData(String starid) {
        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes(starid + "_"));
        //按照ASCII码排序全键盘第二大
        scan.setStopRow(Bytes.toBytes(starid + "_|"));
        List<String> weiBoRowKeys = new ArrayList<String>();

        try {
            ResultScanner scanner = HBaseUtil.getAllData(Name.TABLE_WEIBO.getName(), scan);
            for (Result result : scanner) {
                Cell[] cells = result.rawCells();
                if (cells.length == 5) {
                    break;
                }
                for (Cell cell : cells) {
                    weiBoRowKeys.add(Bytes.toString(CellUtil.cloneRow(cell)));
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weiBoRowKeys;
    }

    //查看微博中根据rowKey集合查出微博信息
    public Map<String,Result> getWeiBos(List<String> rowKeys) throws Exception {
        Map<String,Result> weiBoMap = new HashMap<String, Result>();
        for (String rowKey : rowKeys) {
            Get get = new Get(Bytes.toBytes(rowKey));
            Result result = HBaseUtil.getData(Name.TABLE_WEIBO.getName(), get);
            if(!result.isEmpty()){
                weiBoMap.put(rowKey,result);
            }else{
                weiBoMap.put(rowKey,null);
            }
       }
       return weiBoMap;
    }
}
