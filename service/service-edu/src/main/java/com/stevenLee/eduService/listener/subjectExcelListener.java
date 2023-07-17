package com.stevenLee.eduService.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stevenLee.config.exceptionHandler.guliException;
import com.stevenLee.eduService.entity.EduSubject;
import com.stevenLee.eduService.entity.subjectData;
import com.stevenLee.eduService.service.EduSubjectService;

public class subjectExcelListener extends AnalysisEventListener<subjectData> {
    public EduSubjectService ess;

    public subjectExcelListener() {
    }

    public subjectExcelListener(EduSubjectService ess) {
        this.ess = ess;
    }

    @Override
    public void invoke(subjectData sd, AnalysisContext ac) {
        if(sd == null){
            throw new guliException("文件数据为空",20001);
        }
        EduSubject esOne = this.existOne(ess,sd.getOneSubjectName());
        if(esOne == null){
            esOne = new EduSubject();
            esOne.setParentId("0");
            esOne.setTitle(sd.getOneSubjectName());
            ess.save(esOne);
        }
        String pid = esOne.getId();
        EduSubject esTwo = this.existTwo(ess,sd.getTwoSubjectName(),pid);
        if(esTwo == null){
            esTwo = new EduSubject();
            esTwo.setParentId(pid);
            esTwo.setTitle(sd.getTwoSubjectName());
            ess.save(esTwo);
        }

    }
    //判断一级分类不能重复添加
    private EduSubject existOne(EduSubjectService ess,String name){
        QueryWrapper<EduSubject> qw = new QueryWrapper<>();
        qw.eq("title",name);
        qw.eq("parent_id","0");
        EduSubject es = ess.getOne(qw);
        return es;
    }
    private EduSubject existTwo(EduSubjectService ess,String name,String parent){
        QueryWrapper<EduSubject> qw = new QueryWrapper<>();
        qw.eq("title",name);
        qw.eq("parent_id",parent);
        EduSubject es = ess.getOne(qw);
        return es;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
