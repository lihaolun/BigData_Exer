package com.atguigu.bigdata.project.weibo.service;

import com.atguigu.bigdata.common.constant.Column;
import com.atguigu.bigdata.common.util.HBaseUtil;
import com.atguigu.bigdata.project.weibo.dao.AdminDao;
import com.atguigu.bigdata.project.weibo.dao.InboxDao;
import com.atguigu.bigdata.project.weibo.dao.RelationDao;
import com.atguigu.bigdata.project.weibo.dao.WeiBoDao;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class WeiBoService {
    private AdminDao adminDao = new AdminDao();
    private WeiBoDao weiBoDao = new WeiBoDao();
    private RelationDao relationDao = new RelationDao();
    private InboxDao inboxDao = new InboxDao();
    /**
     * 创建命名空间
     * @param namespace
     */
    public void createNameSpace(String namespace) {

        try {
            boolean flag = adminDao.isExistNameSpace(namespace);
            if(!flag){
                adminDao.createNameSpace(namespace);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                adminDao.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 建表
     * @param tableName
     * @param columnFamilyName
     */
    public void createTable(String tableName, String ...columnFamilyName) {
        adminDao.createTable(tableName,1,columnFamilyName);
    }
    /**
     * 建表
     * @param tableName
     * @param columnFamilyName
     */
    public void createTable(String tableName,int versions, String ...columnFamilyName) {
        try {
            boolean flag = adminDao.isTableExist(tableName);
            if(!flag){
                adminDao.createTable(tableName,versions,columnFamilyName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                adminDao.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发布微博
     * @param userid
     * @param content
     * @param timestamp
     */
    public void publishWeibo(String userid, String content, long timestamp) {
        //发布到weibo表
        weiBoDao.addData(userid,content,timestamp);
        //获取用户的所有粉丝id
        List<String>  ids = relationDao.getFanIds(userid);
        //推送到粉丝收件箱
        if (ids != null) {
            inboxDao.addData(ids,userid,userid+"_"+(Long.MAX_VALUE-timestamp));
        }
    }

    /**
     * 关注用户
     * @param fanid
     * @param starid
     */
    public void attendUser(String fanid, String starid) {
        //粉丝关注明星
        relationDao.addAttendsData(fanid, Column.COLUMN_FAMILY_ATTENDS.getName(),starid,starid);
        //明星粉丝列表增加粉丝
        relationDao.addFansData(starid,Column.COLUMN_FAMILY_FANS.getName(),fanid,fanid);
        //查询明星发布的微博数据
        List<String> weiBoRowKeys = weiBoDao.scanWeiBoData(starid);
        //推送明星微博
        inboxDao.addData(fanid,starid,weiBoRowKeys);
    }

    /**
     * 关注之后查看微博
     * @param userid
     * @param starid
     */
    public void viewWeiBo(String userid, String starid) {
        try {
            //查询粉丝收件箱里的微博数据（weibo的rowKey）
            List<String> rowKeys = new ArrayList<String>();
            Result result = inboxDao.getDatas(userid, starid);
            for (Cell cell : result.rawCells()) {
                rowKeys.add(Bytes.toString(CellUtil.cloneValue(cell)));
            }

            //从微博表获取指定rowKey的数据(可以用Result接收，但是为了方便迭代用Map)
            Map<String,Result>  resultMap = weiBoDao.getWeiBos(rowKeys);
            //如果查不到则给出提示
            Iterator<String> iterator = resultMap.keySet().iterator();
            while(iterator.hasNext()){
                String key = iterator.next();
                Result keyResult = resultMap.get(key);
                Cell[] cells = keyResult.rawCells();
                if (cells == null) {
                    for (Cell cell : cells) {
                        System.out.println("微博内容为"+CellUtil.cloneValue(cell));
                    }
                }else {
                    System.out.println("微博内容不存在或已删除");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取消关注
     * @param fanid
     * @param starid
     */
    public void removeAttend(String fanid, String starid) {
        try {
            //将粉丝关注的明星删除
            relationDao.deleteAttendData(Column.COLUMN_FAMILY_ATTENDS.getName(),fanid,starid);
            //删除明星的粉丝
            relationDao.deleteFansData(Column.COLUMN_FAMILY_FANS.getName(),starid,fanid);
            //删除收件箱中明星的微博信息
            inboxDao.deleteWeiBoData(fanid,starid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 明星删除微博
     */
    public void removeWeiBo(){
        //删除微博表中的微博数据

        //获取明星所有粉丝数据

        //删除所有粉丝收件箱中的微博数据
    }

}
