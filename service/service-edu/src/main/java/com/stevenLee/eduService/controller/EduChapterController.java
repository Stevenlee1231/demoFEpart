package com.stevenLee.eduService.controller;


import com.stevenLee.commonUtils.R;
import com.stevenLee.eduService.entity.EduChapter;
import com.stevenLee.eduService.entity.chapter.chapterVo;
import com.stevenLee.eduService.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-07-14
 */
@RestController
@RequestMapping("/eduService/edu-chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService ecs;
    //根据课程ID获取章节
    @GetMapping("getChapter/{courseId}")
    public R getChapter(@PathVariable String courseId){
        List<chapterVo> list = ecs.getChapter(courseId);
        return R.ok().data("list",list);
    }
    //添加章节
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter ec){
        ecs.save(ec);
        return R.ok();
    }
    //根据章节ID查询
    @GetMapping("getChapterById/{chapterId}")
    public R getChapterById(@PathVariable String chapterId){
        EduChapter ec = ecs.getById(chapterId);
        return R.ok().data("chapter",ec);
    }
    //修改章节
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter ec){
        ecs.updateById(ec);
        return R.ok();
    }
    //删除章节
    @DeleteMapping("deleteChapter/{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){
        boolean flag = ecs.deleteChapter(chapterId);
        if(flag)
            return R.ok();
        else
            return R.error();
    }
}

