package com.stevenLee.eduService.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stevenLee.commonUtils.R;
import com.stevenLee.eduService.entity.EduCourse;
import com.stevenLee.eduService.entity.EduTeacher;
import com.stevenLee.eduService.entity.courseQuery;
import com.stevenLee.eduService.entity.vo.publishVo;
import com.stevenLee.eduService.service.EduCourseService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.stevenLee.eduService.entity.vo.courseVo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-07-14
 */
@RestController
@RequestMapping("/eduService/edu-course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService ecs;
    @PostMapping("addCourse")
    public R addCourse(@RequestBody courseVo cv) {
        String id = ecs.saveCourseVo(cv);
        return R.ok().data("id",id);
    }
    //根据ID查询课程
    @GetMapping("getCourse/{courseId}")
    public R getCourse(@PathVariable String courseId){
        courseVo cv = ecs.getCourse(courseId);
        return R.ok().data("cv",cv);
    }
    @PostMapping("updateCourse")
    public R updateCourse(@RequestBody courseVo cv){
        ecs.updateCourse(cv);
        return R.ok();
    }
    //根据ID查询所有课程信息
    @GetMapping("getPublish/{courseId}")
    public R getPublish(@PathVariable String courseId){
        publishVo pv = ecs.getPublish(courseId);
        return R.ok().data("pv",pv);
    }
    //修改课程状态
    @PostMapping("updatePublish/{courseId}")
    public R updatePublish(@PathVariable String courseId){
        EduCourse ec = new EduCourse();
        ec.setId(courseId);
        ec.setStatus("Normal");
        ecs.updateById(ec);
        return R.ok();
    }
    //分页查询
    @PostMapping("pageQuery/{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit, @RequestBody courseQuery cq){
        Page<EduCourse> pageParam = new Page<>(page, limit);
        ecs.pageQuery(pageParam, cq);
        List<EduCourse> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        System.out.println(cq.getTitle());
        return R.ok().data("total", total).data("rows", records);
    }
    @DeleteMapping("deleteCourse/{courseId}")
    public R deleteCourse(@PathVariable String courseId){
        ecs.deleteCourse(courseId);
        return R.ok();
    }

}

