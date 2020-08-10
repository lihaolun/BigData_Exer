package com.atguigu.bigdata.project.ct.analysis;

import com.atguigu.bigdata.project.ct.analysis.tool.AnalysisBeanTool;
import org.apache.hadoop.util.ToolRunner;

public class AnalysisData {
    public static void main(String[] args) {
        try {
            ToolRunner.run(new AnalysisBeanTool(),args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
