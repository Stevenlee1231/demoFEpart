package com.stevenLee.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.stevenLee.commonUtils.R;
import com.stevenLee.config.exceptionHandler.guliException;
import com.stevenLee.vod.service.vodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.stevenLee.vod.utils.initVod;
import com.stevenLee.vod.utils.constantPropertiesUtils;

import java.util.List;

@RestController
@RequestMapping("/eduVod")
@CrossOrigin
public class vodController {
    @Autowired
    private vodService vs;
    @PostMapping("uploadVideo")
    public R uploadVideo(MultipartFile file){
        String videoId = vs.uploadVideo(file);
        return R.ok().data("videoId",videoId);
    }
    @DeleteMapping("deleteVideoById/{id}")
    public R deleteVideoById(@PathVariable String id){
        try{
            DefaultAcsClient dac = initVod.initVodClient(constantPropertiesUtils.ACCESS_KEY_ID,constantPropertiesUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            dac.getAcsResponse(request);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            throw new guliException("删除失败",20001);
        }
    }
    @DeleteMapping("deleteVideosById")
    public R deleteVideosById(@RequestParam("videoId") List<String> videoId){
        vs.deleteVideosById(videoId);
        return R.ok();
    }
}
