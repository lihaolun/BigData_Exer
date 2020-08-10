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

public class RelationDao {
    /**
     * 获取用户的所有粉丝id
     * @param userid
     * @return
     */
    public List<String> getFanIds(String userid) {
        byte[] rowKey = Bytes.toBytes(userid);
        byte[] columnFamily = Bytes.toBytes(Column.COLUMN_FAMILY_FANS.getName());
        List<String> fasIds = new ArrayList<>();
        Get get = new Get(rowKey);
        get.addFamily(columnFamily);

        try {
            Result result = HBaseUtil.getData(Name.TABLE_RELATION.getName(), get);
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                fasIds.add(Bytes.toString(CellUtil.cloneQualifier(cell)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fasIds;
    }

    public void addAttendsData(String fanid, String columnFamily, String starid,String val) {
        byte[] rowKey = Bytes.toBytes(fanid);
        Put put = new Put(rowKey);
        put.addColumn(Bytes.toBytes(columnFamily),Bytes.toBytes(starid),Bytes.toBytes(val));
        try {
            HBaseUtil.addData(Name.TABLE_RELATION.getName(),put);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addFansData(String starid, String columnFamily, String fanid, String val) {
        byte[] rowKey = Bytes.toBytes(starid);
        Put put = new Put(rowKey);
        put.addColumn(Bytes.toBytes(columnFamily),Bytes.toBytes(fanid),Bytes.toBytes(val));
        try {
            HBaseUtil.addData(Name.TABLE_RELATION.getName(),put);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取消关注中删除关注的明星
     * @param name
     * @param fanid
     * @param starid
     */
    public void deleteAttendData(String name, String fanid, String starid) throws Exception {
        Delete delete = new Delete(Bytes.toBytes(fanid));
        delete.addColumn(Bytes.toBytes(fanid),Bytes.toBytes(starid));
        HBaseUtil.deleteData(Name.TABLE_RELATION.getName(),delete);
   }

    /**
     * 取消关注中删除粉丝
     * @param name
     * @param starid
     * @param fanid
     */
    public void deleteFansData(String name, String starid, String fanid) throws Exception {
        Delete delete = new Delete(Bytes.toBytes(fanid));
        delete.addColumn(Bytes.toBytes(starid),Bytes.toBytes(starid));
        HBaseUtil.deleteData(Name.TABLE_RELATION.getName(),delete);
    }

}
