package com.atguigu.bigdata.project.ct.analysis.io;

import com.atguigu.bigdata.project.ct.analysis.bean.OutputKey;
import com.atguigu.bigdata.project.ct.analysis.bean.OutputValue;
import com.atguigu.bigdata.project.ct.common.util.JDBCUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MySQLBeanOutputFormat extends OutputFormat<OutputKey,OutputValue>{
    private FileOutputCommitter outputCommitter = null;

    protected static class MySQLRecordWriter extends RecordWriter<OutputKey,OutputValue>{
        private Map<String,Integer> contactMap = new HashMap<String,Integer>();
        private Map<String,Integer> dateMap = new HashMap<String,Integer>();
        private Connection connection;
        public MySQLRecordWriter() {
            connection = JDBCUtil.getConnection();
            //查询联系人和日期信息
            String contactSql = "select id,telephone from t_contacts";
            String dateSql = "select id,year,month,day from t_date";
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                preparedStatement = connection.prepareStatement(contactSql);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    int id = resultSet.getInt(1);

                    String tel = resultSet.getString(2);
                    contactMap.put(tel,id);
                }
                resultSet.close();
                preparedStatement.close();
                preparedStatement = connection.prepareStatement(dateSql);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    int id = resultSet.getInt(1);
                    String year = resultSet.getString(2);
                    String month = resultSet.getString(3);
                    String day = resultSet.getString(4);
                    if (month.length() ==1){
                        month = "0"+month;
                    }
                    if (day.length() ==1){
                        day = "0"+day;
                    }
                    dateMap.put(year+month+day,id);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(resultSet!=null){
                        resultSet.close();
                    }
                    if(preparedStatement!=null){
                        preparedStatement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void write(OutputKey key, OutputValue value) throws IOException, InterruptedException {
            //向mysql中增加数据
            String callSql = "insert into t_call (cid,dateid,sumcount,sumduration) values(?,?,?,?)";
            PreparedStatement preparedStatement = null;
            String tel = key.getCall();
            String date = key.getDate();
            String sumCount = value.getSumcount();
            String sumDuration = value.getSumduration();

            Integer cid = contactMap.get(tel);
            Integer dateid = dateMap.get(date);
            if (dateid == null) {
                dateid = 10;
            }
            Integer sumcount = Integer.valueOf(sumCount);
            Integer sumduration = Integer.valueOf(sumDuration);
            try {
                preparedStatement = connection.prepareStatement(callSql);
                preparedStatement.setInt(1,cid);
                preparedStatement.setInt(2,dateid);
                preparedStatement.setInt(3,sumcount);
                preparedStatement.setInt(4,sumduration);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                if (preparedStatement!=null){
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        @Override
        public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public RecordWriter getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        MySQLRecordWriter recordWriter = new MySQLRecordWriter();
        return recordWriter;
    }

    @Override
    public void checkOutputSpecs(JobContext jobContext) throws IOException, InterruptedException {

    }

    @Override
    public OutputCommitter getOutputCommitter(TaskAttemptContext context) throws IOException, InterruptedException {
        if (outputCommitter == null) {
            Path output = getOutputPath(context);
            outputCommitter = new FileOutputCommitter(output,context);
        }
        return outputCommitter;
    }

    public static Path getOutputPath(JobContext job) {
        String name = job.getConfiguration().get(FileOutputFormat.OUTDIR);
        return name == null ? null : new Path(name);
    }
}
