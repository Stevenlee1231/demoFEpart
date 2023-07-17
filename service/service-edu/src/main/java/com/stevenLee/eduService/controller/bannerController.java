package com.stevenLee.eduService.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stevenLee.commonUtils.R;
import com.stevenLee.eduService.entity.EduCourse;
import com.stevenLee.eduService.entity.EduTeacher;
import com.stevenLee.eduService.service.EduCourseService;
import com.stevenLee.eduService.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/eduService/banner")
@CrossOrigin
public class bannerController {
    @Autowired
    private EduCourseService ecs;
    @Autowired
    private EduTeacherService ets;
    @GetMapping("index")
    public R index(){
        QueryWrapper<EduCourse> qw1 = new QueryWrapper<>();
        qw1.orderByDesc("view_count");
        qw1.last("limit 8");
        List<EduCourse> res1 = ecs.list(qw1);
        QueryWrapper<EduTeacher> qw2 = new QueryWrapper<>();
        qw2.orderByDesc("id");
        qw2.last("limit 4");
        List<EduTeacher> res2 = ets.list(qw2);
        return R.ok().data("res1",res1).data("res2",res2);
    }
}
