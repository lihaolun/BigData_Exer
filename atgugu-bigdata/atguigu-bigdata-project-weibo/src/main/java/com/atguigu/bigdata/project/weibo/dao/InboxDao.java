package com.atguigu.bigdata.project.weibo.dao;

import com.atguigu.bigdata.common.constant.Column;
import com.atguigu.bigdata.common.constant.Name;
import com.atguigu.bigdata.common.util.HBaseUtil;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.List;

public class InboxDao {
    /**
     * 发布微博向inbox里推送微博
     *
     * @param ids
     * @param userid
     * @param weiboId
     */
    public void addData(List<String> ids, String userid, String weiboId) {
        List<Put> puts = new ArrayList<>();
        for (String id : ids) {
            byte[] rowKey = Bytes.toBytes(id);
            byte[] columnFamily = Bytes.toBytes(Column.COLUMN_FAMILY_INFO.getName());
            byte[] column = Bytes.toBytes(userid);
            byte[] val = Bytes.toBytes(weiboId);
            Put put = new Put(rowKey);
            put.addColumn(columnFamily, column, val);
            puts.add(put);
        }
        try {
            HBaseUtil.addData(Name.TABLE_INBOX.getName(), puts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关注明星增加明星最近微博
     *
     * @param fanid
     * @param starid
     * @param weiBoRowKeys
     */
    public void addData(String fanid, String starid, List<String> weiBoRowKeys) {
        List<Put> puts = new ArrayList<>();
        byte[] rowKey = Bytes.toBytes(fanid);
        byte[] columnFamily = Bytes.toBytes(Column.COLUMN_FAMILY_INFO.getName());
        byte[] column = Bytes.toBytes(starid);
        try {
            for (String weiBoRowKey : weiBoRowKeys) {
                byte[] val = Bytes.toBytes(weiBoRowKey);
                Put put = new Put(rowKey);
                //放微博的时候为了排序，取最近的数据，所以将rowKey在weibo中倒序排，现在再倒回来成正序
                long ts = Long.MAX_VALUE - Long.valueOf(weiBoRowKey.split("-")[1]);
                put.addColumn(columnFamily, column, val);
                puts.add(put);
            }
            HBaseUtil.addData(Name.TABLE_INBOX.getName(), puts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //关注之后查看微博数据，返回微博集合
    public Result getDatas(String userid, String starid) {
        try {
            Get get = new Get(Bytes.toBytes(userid));
            get.setMaxVersions(5);
            get.addColumn(Bytes.toBytes(Column.COLUMN_FAMILY_INFO.getName()), Bytes.toBytes(starid));
            Result result = HBaseUtil.getData(Name.TABLE_INBOX.getName(), get);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 取消关注中删除微博信息
     *
     * @param fanid
     * @param starid
     */
    public void deleteWeiBoData(String fanid, String starid) throws Exception {
        Delete delete = new Delete(Bytes.toBytes(fanid));
        delete.addColumn(Bytes.toBytes(Column.COLUMN_FAMILY_INFO.getName()), Bytes.toBytes(starid));
        HBaseUtil.deleteData(Name.TABLE_RELATION.getName(), delete);
    }
}
