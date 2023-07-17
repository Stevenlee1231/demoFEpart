package myGerenator.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class testEasyExcel {
    public static void main(String[] args) {
        String fileName = "D:\\write.xlsx";
        EasyExcel.read(fileName,demoData.class,new excelListener()).sheet().doRead();
    }
    private static List<demoData> getData(){
        List<demoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            demoData data = new demoData();
            data.setSno(i);
            data.setSname("test"+i);
            list.add(data);
        }
        return list;
    }
}
