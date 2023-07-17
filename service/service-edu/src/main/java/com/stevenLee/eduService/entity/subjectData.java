package com.stevenLee.eduService.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class subjectData {
    @ExcelProperty(index=0)
    private String oneSubjectName;
    @ExcelProperty(index=1)
    private String twoSubjectName;
}
