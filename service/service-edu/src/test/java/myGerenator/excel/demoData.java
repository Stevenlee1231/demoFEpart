package myGerenator.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class demoData {
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sno;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;
}
