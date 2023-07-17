package com.stevenLee.eduService.controller;


import com.stevenLee.commonUtils.R;
import com.stevenLee.eduService.entity.subject.firstSubject;
import com.stevenLee.eduService.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-07-13
 */
@RestController
@RequestMapping("/eduService/edu-subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService ess;
    //添加课表
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        ess.saveSubject(file,ess);
        return R.ok();
    }
    //获取课表分类
    @GetMapping("getSubject")
    public R getSubject(){
        List<firstSubject> list = ess.getAllSubject();
        return R.ok().data("list",list);
    }
}

