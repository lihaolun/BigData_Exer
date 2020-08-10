package com.atguigu.bigdata.project.weibo.dao;

import com.atguigu.bigdata.common.util.HBaseUtil;

public class AdminDao {

    public void createNameSpace(String namespace) {
        try {
            HBaseUtil.createNameSpace(namespace);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isExistNameSpace(String namespace) {
        try {
            return HBaseUtil.isExistNameSpace(namespace);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void close() {
        try {
            HBaseUtil.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isTableExist(String tableName){
        try {
            return  HBaseUtil.isTableExist(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createTable(String tableName, String[] columnFamilyName) {
        try {
            HBaseUtil.createTable(tableName,1,columnFamilyName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName,int versions, String[] columnFamilyName) {
        try {
            HBaseUtil.createTable(tableName,versions,columnFamilyName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
