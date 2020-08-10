package com.atguigu.bigdata.project.ct.common.util;

import com.atguigu.bigdata.project.ct.common.constant.Formats;
import com.atguigu.bigdata.project.ct.common.constant.Names;
import com.atguigu.bigdata.project.ct.common.constant.Vals;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;


public class HBaseUtil {
    private static ThreadLocal<Connection> connectHolder = new ThreadLocal<Connection>();
    private static ThreadLocal<Admin> adminHolder = new ThreadLocal<Admin>();

    public static void start() throws Exception {
        Connection connection = connectHolder.get();
        if (connection == null) {
            connection = ConnectionFactory.createConnection();
            connectHolder.set(connection);
        }

        Admin admin = adminHolder.get();
        if (admin == null) {
            admin = connection.getAdmin();
            adminHolder.set(admin);
        }
    }

    public static void end() throws Exception {
        Admin admin = adminHolder.get();
        if (admin != null) {
            admin.close();
            adminHolder.remove();
        }

        Connection connection = connectHolder.get();
        if (connection != null && !connection.isClosed()) {
            connection.close();
            connectHolder.remove();
        }
    }

    /**
     * 创建命名空间
     *
     * @param namespace
     */
    public static void createNamespace(String namespace) throws Exception {
        Admin admin = adminHolder.get();
        NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(namespace).build();
        admin.createNamespace(namespaceDescriptor);
    }

    /**
     * 创建表
     *
     * @param tableName
     */
    public static void createTable(String tableName) throws Exception {
        createTable(tableName, 1);
    }

    /**
     * 创建表
     *
     * @param tableName
     */
    public static void createTable(String tableName, int regionCount) throws Exception {
        createTable(tableName, regionCount, null);
    }

    /**
     * 创建表
     *
     * @param tableName
     * @param regionCount
     * @throws Exception
     */
    public static void createTable(String tableName, int regionCount, String coprocesserClass) throws Exception {
        Admin admin = adminHolder.get();
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        HColumnDescriptor columnDescriptor = new HColumnDescriptor(Names.TABLE_FAMILY_INFO.value());
        hTableDescriptor.addFamily(columnDescriptor);
        HColumnDescriptor columnDescriptor1 = new HColumnDescriptor(Names.TABLE_FAMILY_UNACTIVE.value());
        hTableDescriptor.addFamily(columnDescriptor1);
        if (coprocesserClass != null) {
            hTableDescriptor.addCoprocessor(coprocesserClass);
        }
        if (regionCount == 0) {
            admin.createTable(hTableDescriptor);
        } else {
            byte[][] splitkeys = genSplitkeys(regionCount);
            admin.createTable(hTableDescriptor, splitkeys);
        }
    }


    /**
     * 获取分区键
     *
     * @param regionCount
     * @return
     */
    public static byte[][] genSplitkeys(int regionCount) {
        int splitKeyCount = regionCount - 1;
        List<byte[]> bytes = new ArrayList<byte[]>();
        for (int i = 0; i < splitKeyCount; i++) {
            //[-∞,0|),[0,1|),[1,+∞)
            String splitKey = i + "|";
            bytes.add(Bytes.toBytes(splitKey));
        }
        Collections.sort(bytes, new Bytes.ByteArrayComparator());
        byte[][] splitkeys = new byte[splitKeyCount][];
        bytes.toArray(splitkeys);
        return splitkeys;
    }

    /**
     * 删除表
     *
     * @param tableName
     * @throws IOException
     */
    public static void deleteTable(String tableName) throws IOException {
        Admin admin = adminHolder.get();
        TableName tName = TableName.valueOf(tableName);
        admin.disableTable(tName);
        admin.deleteTable(tName);
    }

    /**
     * 创建命名空间，如果存在，不做处理
     *
     * @param namespace
     */
    public static void createNamespaceNX(String namespace) throws Exception {
        Admin admin = adminHolder.get();
        try {
            admin.getNamespaceDescriptor(namespace);
        } catch (IOException e) {
            createNamespace(namespace);
        }
    }

    /**
     * 创建表，如果表存在，那么删除重新创建
     *
     * @param tableName
     */
    public static void createTableXX(String tableName, int regionCount, String className) throws Exception {
        Admin admin = adminHolder.get();
        if (admin.tableExists(TableName.valueOf(tableName))) {
            deleteTable(tableName);
        }
        createTable(tableName, regionCount, className);
    }

    /**
     * 获取表
     *
     * @param tableName
     * @return
     */
    public static Table getTable(String tableName) throws Exception {
        Connection connection = connectHolder.get();
        Table table = connection.getTable(TableName.valueOf(tableName));
        return table;
    }

    /**
     * 获取rowKey的分区号
     *
     * @param call
     * @param date
     * @return
     */
    public static String genRegionNum(String call, String date) {

        //20180101000000
        String yearMonth = date.substring(0, 6);

        //获取电话号码后四位作为用户标识，为了让数据均匀分配，无规律
        String usercode = call.substring(call.length() - 4);
        //反转用户标识
        StringBuilder stringBuilder = new StringBuilder(usercode);
        usercode = stringBuilder.reverse().toString();
        int callHashCode = usercode.hashCode();
        int yearMonthHashCode = yearMonth.hashCode();

        //异或算法
        int crc = Math.abs(callHashCode ^ yearMonthHashCode);
        int regionNum = crc % Vals.INT_6.intValue();
        return regionNum + "";
    }

    /**
     * 用startRow,stopRow的方法查询rowKey范围集合数据
     * @param call
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<String[]> getStartStopRows(String call, String startDate, String endDate){
        //201805 — 201808
        //201805 — 201805|
        //201806 — 201806|
        List<String[]> rowList = new ArrayList<String[]>();
        try {
            Calendar sd = Calendar.getInstance();
            sd.setTime(DateUtil.parse(startDate, Formats.DATE_YEARMONTH));
            Calendar ed = Calendar.getInstance();
            ed.setTime(DateUtil.parse(endDate, Formats.DATE_YEARMONTH));

            while (sd.getTimeInMillis() <= ed.getTimeInMillis()) {
                String nowDate = DateUtil.format(sd.getTime(), Formats.DATE_YEARMONTH);
                String regionNum = genRegionNum(call, nowDate);
                //1_138_201803
                String startRow = regionNum + "_" + call + "_" + nowDate;
                //1_138_201803|
                String stopRow = startRow + "|";

                String [] rows = {startRow,stopRow};
                rowList.add(rows);
                sd.add(Calendar.MONTH,1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowList;
    }
}
