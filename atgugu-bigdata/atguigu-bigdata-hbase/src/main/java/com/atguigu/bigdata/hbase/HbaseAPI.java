package com.atguigu.bigdata.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HbaseAPI {
    private static ThreadLocal<Admin> adminLocals = new ThreadLocal<Admin>();
    private static ThreadLocal<Connection> connectsLocal = new ThreadLocal<Connection>();

    public static void main(String[] args) throws Exception {
        boolean flag1 = isTableExist("student");
        boolean flag2 = isTableExist("bigdata:student");
        System.out.println("flag1:"+flag1+"---flag2:"+flag2);

/*
        boolean flag3 = isExistNameSpace("bigdata");
        if(!flag3){
            createNameSpace("bigdata");
            System.out.println("命名空间创建成功");
        }
*/

     /*   createTable("bigdata:student","info");
        System.out.println("bigdata中的student创建成功");*/

       /*dropTable("bigdata:student");*/
        addData("bigdata:student","info","1001","name","faker");
        addData("bigdata:student","info","1002","name","bang");
        deleteData("bigdata:student","1002");
        getAllData("bigdata:student");
        getData("bigdata:student","1002");

        close();
    }

    /**
     * 判断表是否存在
     * @param tableName
     * @return
     * @throws Exception
     */
    public static boolean isTableExist(String tableName) throws  Exception{
        return  getAdmin().tableExists(TableName.valueOf(tableName));
    }

    /**
     * 创建表
     * @param tableName
     * @param columnFamily
     * @param columnFamiles
     * @throws Exception
     */
    public static void createTable(String tableName,String columnFamily,String ...columnFamiles) throws Exception {
        //获取表格描述器
        HTableDescriptor table = new HTableDescriptor(TableName.valueOf(tableName));
        //获取列描述器
        HColumnDescriptor column = new HColumnDescriptor(columnFamily);
        //增加列族
        table.addFamily(column);

        for (String columnFamile : columnFamiles) {
            HColumnDescriptor columnDescriptor = new HColumnDescriptor(columnFamile);
            table.addFamily(columnDescriptor);
        }
        getAdmin().createTable(table);
    }

    /**
     * 删除表
     * @param tableName
     * @throws Exception
     */
    public static void  dropTable(String tableName) throws Exception {
        Admin admin = getAdmin();

        admin.disableTable(TableName.valueOf(tableName));

        admin.deleteTable(TableName.valueOf(tableName));
    }

    /**
     * 查询表的所有数据
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
                System.out.println(rowKey+"-"+family+"-"+column +"-"+value);
            }
        }
    }

    /**
     * 获取某一rowKey的数据
     * @param tableName
     * @param rowKey
     * @throws Exception
     */
    public static void getData(String tableName,String rowKey) throws Exception {

        Table table = getConnection().getTable(TableName.valueOf(tableName));

        Get get = new Get(Bytes.toBytes(rowKey));

        Result result = table.get(get);

        Cell[] cells = result.rawCells();

        for (Cell cell : cells) {
            String family = Bytes.toString(CellUtil.cloneFamily(cell));
            String column = Bytes.toString(CellUtil.cloneQualifier(cell));
            String value = Bytes.toString(CellUtil.cloneValue(cell));
            System.out.println(rowKey+"-"+family+"-"+column+"-"+value);
        }
    }
    /**
     * 创建命名空间
     * @param namespace
     * @throws Exception
     */
    public static void createNameSpace(String namespace) throws Exception {

        Admin admin = getAdmin();

        NamespaceDescriptor build = NamespaceDescriptor.create(namespace).build();

        admin.createNamespace(build);
    }

    /**
     * 判断是否存在指定的命名空间
     * @param nameSpace
     * @throws Exception
     */
    public static boolean isExistNameSpace(String nameSpace) throws  Exception{
        Admin admin = getAdmin();
        try {
            NamespaceDescriptor namespaceDescriptor = admin.getNamespaceDescriptor(nameSpace);
            return true;
        } catch (NamespaceNotFoundException e) {
            System.out.println("该命名空间不存在");
            return false;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 增加数据
     * @param tableName
     * @param columnFamily
     * @param rowKey
     * @param column
     * @param val
     * @throws Exception
     */
    public static void addData(String tableName,String columnFamily,String rowKey,String column,String val) throws Exception {
        //获取表对象
        Table table = getConnection().getTable(TableName.valueOf(tableName));
        //构造数据
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(columnFamily),Bytes.toBytes(column),Bytes.toBytes(val));
        //增加数据
        table.put(put);
    }

    /**
     * 删除数据
     * @param tableName
     * @param rowKey
     * @throws Exception
     */
    public static void deleteData(String tableName,String rowKey) throws Exception {
        //获取表对象
        Table table = getConnection().getTable(TableName.valueOf(tableName));
        //构造删除的数据
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        //删除数据
        table.delete(delete);
    }




    /***
     * 获取管理对象
     * @return
     */
    public static Admin getAdmin() throws  Exception{
        Admin admin = adminLocals.get();
        if(admin == null){
            admin =  getConnection().getAdmin();
            adminLocals.set(admin);
        }
        return admin;
    }

    /**
     * 获取连接
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception{
        Connection connection = connectsLocal.get();
        if(connection == null){
            Configuration config = HBaseConfiguration.create();
            connection = ConnectionFactory.createConnection(config);
            connectsLocal.set(connection);
        }
        return connection;
    }

    /**
     * 关闭资源
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
