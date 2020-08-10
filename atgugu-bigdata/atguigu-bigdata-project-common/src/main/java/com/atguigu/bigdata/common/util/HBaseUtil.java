package com.atguigu.bigdata.common.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class HBaseUtil {

    private static ThreadLocal<Admin> adminLocals = new ThreadLocal<Admin>();
    private static ThreadLocal<Connection> connectsLocal = new ThreadLocal<Connection>();


    /**
     * 判断是否存在指定的命名空间
     *
     * @param nameSpace
     * @throws Exception
     */
    public static boolean isExistNameSpace(String nameSpace) throws Exception {
        Admin admin = getAdmin();
        try {
            NamespaceDescriptor namespaceDescriptor = admin.getNamespaceDescriptor(nameSpace);
            return true;
        } catch (NamespaceNotFoundException e) {
            System.out.println("该命名空间不存在");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 创建命名空间
     *
     * @param namespace
     * @throws Exception
     */
    public static void createNameSpace(String namespace) throws Exception {

        Admin admin = getAdmin();

        NamespaceDescriptor build = NamespaceDescriptor.create(namespace).build();

        admin.createNamespace(build);
    }

    /***
     * 获取管理对象
     * @return
     */
    public static Admin getAdmin() throws Exception {
        Admin admin = adminLocals.get();
        if (admin == null) {
            admin = getConnection().getAdmin();
            adminLocals.set(admin);
        }
        return admin;
    }

    /**
     * 获取连接
     *
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        Connection connection = connectsLocal.get();
        if (connection == null) {
            Configuration config = HBaseConfiguration.create();
            connection = ConnectionFactory.createConnection(config);
            connectsLocal.set(connection);
        }
        return connection;
    }

    /**
     * 判断表是否存在
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    public static boolean isTableExist(String tableName) throws Exception {
        return getAdmin().tableExists(TableName.valueOf(tableName));
    }

    /**
     * 创建表
     *
     * @param tableName
     * @param columnFamiles
     * @throws Exception
     */
    public static void createTable(String tableName, String... columnFamiles) throws Exception {
        createTable(tableName, 1, columnFamiles);
    }

    /**
     * 创建表
     *
     * @param tableName
     * @param columnFamiles
     * @throws Exception
     */
    public static void createTable(String tableName, int versions, String... columnFamiles) throws Exception {
        //获取表格描述器
        HTableDescriptor table = new HTableDescriptor(TableName.valueOf(tableName));
        //获取列描述器
        //HColumnDescriptor column = new HColumnDescriptor(columnFamily);
        //增加列族
        //table.addFamily(column);

        for (String columnFamile : columnFamiles) {
            HColumnDescriptor columnDescriptor = new HColumnDescriptor(columnFamile);
            columnDescriptor.setMaxVersions(versions);
            columnDescriptor.setMinVersions(versions);
            table.addFamily(columnDescriptor);
        }
        getAdmin().createTable(table);
    }

    /**
     * 删除表
     *
     * @param tableName
     * @throws Exception
     */
    public static void dropTable(String tableName) throws Exception {
        Admin admin = getAdmin();

        admin.disableTable(TableName.valueOf(tableName));

        admin.deleteTable(TableName.valueOf(tableName));
    }

    /**
     * 删除数据
     *
     * @param tableName
     * @throws Exception
     */
    public static void deleteData (String tableName, Delete delete) throws Exception {
        //获取表对象
        Table table = getConnection().getTable(TableName.valueOf(tableName));
        //构造删除的数据
        //Delete delete = new Delete(Bytes.toBytes(rowKey));
        //删除数据
        table.delete(delete);
    }

    /**
     * 增加数据
     *
     * @param tableName
     * @param columnFamily
     * @param rowKey
     * @param column
     * @param val
     * @throws Exception
     */
    public static void addData(String tableName, String columnFamily, String rowKey, String column, String val) throws Exception {
        //获取表对象
        Table table = getConnection().getTable(TableName.valueOf(tableName));
        //构造数据
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(val));
        //增加数据
        table.put(put);
    }

    /**
     * 增加数据
     *
     * @param tableName
     * @throws Exception
     */
    public static void addData(String tableName, Put put) throws Exception {
        //获取表对象
        Table table = getConnection().getTable(TableName.valueOf(tableName));
        //增加数据
        table.put(put);
    }

    public static void addData(String tableName, List<Put> puts) throws Exception {
        //获取表对象
        Table table = getConnection().getTable(TableName.valueOf(tableName));
        //增加数据
        table.put(puts);
    }

    /**
     * 查询表的所有数据
     *
     * @param tableName
     * @throws Exception
     */
    public static void getAllData(String tableName) throws Exception {

        Table table = getConnection().getTable(TableName.valueOf(tableName));

        Scan scan = new Scan();

        ResultScanner scanner = table.getScanner(scan);

        for (Result result : scanner) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                String rowKey = Bytes.toString(CellUtil.cloneRow(cell));
                String family = Bytes.toString(CellUtil.cloneFamily(cell));
                String column = Bytes.toString(CellUtil.cloneQualifier(cell));
                String value = Bytes.toString(CellUtil.cloneValue(cell));
                System.out.println(rowKey + "-" + family + "-" + column + "-" + value);
            }
        }
    }

    /**
     * 查询表的所有数据
     *
     * @param tableName
     * @throws Exception
     */
    public static ResultScanner getAllData(String tableName,Scan scan) throws Exception {

        Table table = getConnection().getTable(TableName.valueOf(tableName));

        ResultScanner scanner = table.getScanner(scan);

        return scanner;

    }

    /**
     * 获取某一rowKey的数据
     *
     * @param tableName
     * @param rowKey
     * @throws Exception
     */
    public static void getData(String tableName, String rowKey) throws Exception {

        Table table = getConnection().getTable(TableName.valueOf(tableName));

        Get get = new Get(Bytes.toBytes(rowKey));

        Result result = table.get(get);

        Cell[] cells = result.rawCells();

        for (Cell cell : cells) {
            String family = Bytes.toString(CellUtil.cloneFamily(cell));
            String column = Bytes.toString(CellUtil.cloneQualifier(cell));
            String value = Bytes.toString(CellUtil.cloneValue(cell));
            System.out.println(rowKey + "-" + family + "-" + column + "-" + value);
        }
    }

    /**
     * 获取某一具体column的数据
     *
     * @param tableName
     * @throws Exception
     */
    public static Result getData(String tableName, Get get) throws Exception {

        Table table = getConnection().getTable(TableName.valueOf(tableName));
        return table.get(get);
    }

    /**
     * 关闭资源
     *
     * @throws Exception
     */
    public static void close() throws Exception {
        Admin admin = getAdmin();
        if (admin != null) {
            admin.close();
            adminLocals.remove();
            connectsLocal.remove();
        }
    }
}
