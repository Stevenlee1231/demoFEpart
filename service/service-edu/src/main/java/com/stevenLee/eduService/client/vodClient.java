package com.stevenLee.eduService.client;


import com.stevenLee.commonUtils.R;
import com.stevenLee.eduService.client.impl.vodClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-vod",fallback = vodClientImpl.class)
@Component
public interface vodClient {
    @DeleteMapping("/eduVod/deleteVideoById/{id}")
    public R deleteVideoById(@PathVariable("id") String id);
    @DeleteMapping("/eduVod/deleteVideosById")
    public R deleteVideosById(@RequestParam("videoId") List<String> videoId);
}
