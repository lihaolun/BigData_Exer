package com.atguigu.bigdata.project.ct.consumer.coprocesser;

import com.atguigu.bigdata.project.ct.common.constant.Names;
import com.atguigu.bigdata.project.ct.common.util.HBaseUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.regionserver.wal.WALEdit;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class InsertUnActiveDataCoprocesser extends BaseRegionObserver {
    @Override
    public void postPut(ObserverContext<RegionCoprocessorEnvironment> e, Put put, WALEdit edit, Durability durability) throws IOException {
        String rowKey1 = Bytes.toString(put.getRow());
        // 5_19342117869_17336673697_20181025030221_6170
        String[] values = rowKey1.split("_");
        String call1 = values[1];
        String call2 = values[3];
        String calltime = values[2];
        String duration = values[4];
        String flag = values[5];
        if("1".equals(flag)){
            String rowKey0 = HBaseUtil.genRegionNum(call2,calltime)+"_"+call2+"_"+calltime+"_"+call1+"_"+duration+"_0";
            System.out.println("rowKey0="+rowKey0);

            Put unactivePut = new Put(Bytes.toBytes(rowKey0));
            unactivePut.addColumn(Bytes.toBytes(Names.TABLE_FAMILY_UNACTIVE.value()),Bytes.toBytes("call1"),Bytes.toBytes(call2));
            unactivePut.addColumn(Bytes.toBytes(Names.TABLE_FAMILY_UNACTIVE.value()),Bytes.toBytes("calltime"),Bytes.toBytes(calltime));
            unactivePut.addColumn(Bytes.toBytes(Names.TABLE_FAMILY_UNACTIVE.value()),Bytes.toBytes("call2"),Bytes.toBytes(call1));
            unactivePut.addColumn(Bytes.toBytes(Names.TABLE_FAMILY_UNACTIVE.value()),Bytes.toBytes("duration"),Bytes.toBytes(duration));
            unactivePut.addColumn(Bytes.toBytes(Names.TABLE_FAMILY_UNACTIVE.value()),Bytes.toBytes("flag"),Bytes.toBytes("0"));

            try {
                Table table = e.getEnvironment().getTable(TableName.valueOf(Names.TABLE_CALLLOG.value()));
                table.put(unactivePut);
                table.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }
}
