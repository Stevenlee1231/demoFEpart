package myGerenator.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class excelListener extends AnalysisEventListener<demoData> {
    @Override
    //一行读取
    public void invoke(demoData data, AnalysisContext analysisContext) {
        System.out.println("****"+data);
    }
    //读表头
    public void invokeHeadMap(Map<Integer,String> headMap, AnalysisContext ac){
        System.out.println("表头"+headMap);
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
