package com.stevenLee.eduService.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stevenLee.commonUtils.R;
import com.stevenLee.eduService.entity.EduCourse;
import com.stevenLee.eduService.entity.EduTeacher;
import com.stevenLee.eduService.service.EduCourseService;
import com.stevenLee.eduService.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduService/front-teacher")
@CrossOrigin
public class teacherController {
    @Autowired
    private EduTeacherService ets;
    @Autowired
    private EduCourseService ecs;
    @GetMapping("/{page}/{limit}")
    public R pageList(@PathVariable Long page, @PathVariable Long limit){
        Page<EduTeacher> pageParam = new Page<>();
        Map<String,Object> map = ets.pageList(pageParam);
        return R.ok().data(map);
    }
    @GetMapping("/getInfo/{teacherId}")
    public R getInfo(@PathVariable Long teacherId){
        EduTeacher et = ets.getById(teacherId);
        QueryWrapper<EduCourse> qw = new QueryWrapper<>();
        qw.eq("teacher_id",teacherId);
        List<EduCourse> list = ecs.list(qw);
        return R.ok().data("et",et).data("list",list);
    }

}
