package com.stevenLee.eduService.controller;


import com.alibaba.excel.util.StringUtils;
import com.stevenLee.commonUtils.R;
import com.stevenLee.config.exceptionHandler.guliException;
import com.stevenLee.eduService.client.vodClient;
import com.stevenLee.eduService.entity.EduChapter;
import com.stevenLee.eduService.entity.EduVideo;
import com.stevenLee.eduService.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-07-14
 */
@RestController
@RequestMapping("/eduService/edu-video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService evs;
    @Autowired
    private vodClient vc;
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo ev){
        evs.save(ev);
        return R.ok();
    }
    @DeleteMapping("deleteVideo/{videoId}")
    public R deleteVideo(@PathVariable String videoId){
        String s = evs.getById(videoId).getVideoSourceId();
        if(!StringUtils.isEmpty(s)) {
            R r = vc.deleteVideoById(s);
            if(r.getCode() == 20001)
                throw new guliException("熔断器",20001);
        }
        evs.removeById(videoId);
        return R.ok();
    }
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo ev){
        evs.updateById(ev);
        return R.ok();
    }
    @GetMapping("getVideoById/{videoId}")
    public R getChapterById(@PathVariable String videoId){
        EduVideo ev = evs.getById(videoId);
        return R.ok().data("ev",ev);
    }
}

