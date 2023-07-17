package com.stevenLee.eduService.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stevenLee.commonUtils.R;
import com.stevenLee.eduService.entity.EduCourse;
import com.stevenLee.eduService.entity.chapter.chapterVo;
import com.stevenLee.eduService.entity.vo.front.courseQueryVo;
import com.stevenLee.eduService.entity.vo.front.courseWebVo;
import com.stevenLee.eduService.service.EduChapterService;
import com.stevenLee.eduService.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduService/front-course")
@CrossOrigin
public class courseController {
    @Autowired
    private EduCourseService ecs;
    @Autowired
    private EduChapterService echs;
    @PostMapping("{page}/{limit}")
    public R pageList(@PathVariable Long page, @PathVariable Long limit, @RequestBody(required = false) courseQueryVo cqv){
        Page<EduCourse> pageParam = new Page<>(page, limit);
        Map<String, Object> map = ecs.pageList(pageParam, cqv);
        return R.ok().data(map);
    }
    @GetMapping("getInfo/{courseId}")
    public R getInfo(@PathVariable String courseId){
        courseWebVo cwv = ecs.getInfo(courseId);
        List<chapterVo> list = echs.getChapter(courseId);
        return R.ok().data("cwv",cwv).data("list",list);
    }
}
