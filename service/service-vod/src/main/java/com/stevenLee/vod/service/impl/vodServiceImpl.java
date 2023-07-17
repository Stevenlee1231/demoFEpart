package com.stevenLee.vod.service.impl;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.stevenLee.commonUtils.R;
import com.stevenLee.config.exceptionHandler.guliException;
import com.stevenLee.vod.service.vodService;
import com.stevenLee.vod.utils.initVod;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.stevenLee.vod.utils.constantPropertiesUtils;

import java.io.InputStream;
import java.util.List;

@Service
public class vodServiceImpl implements vodService {
    @Override
    public String uploadVideo(MultipartFile file) {
        try{
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0,fileName.lastIndexOf("."));
            InputStream is = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(constantPropertiesUtils.ACCESS_KEY_ID, constantPropertiesUtils.ACCESS_KEY_SECRET, title, fileName, is);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
            String videoId = null;
            if (response.isSuccess())
                videoId = response.getVideoId();
            else //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            return videoId;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteVideosById(List<String> list) {
        System.out.println(list);
        try{
            DefaultAcsClient dac = initVod.initVodClient(constantPropertiesUtils.ACCESS_KEY_ID,constantPropertiesUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            String s = StringUtils.join(list.toArray(),",");
            request.setVideoIds(s);
            dac.getAcsResponse(request);
        }catch (Exception e){
            e.printStackTrace();
            throw new guliException("删除失败",20001);
        }
    }
}
