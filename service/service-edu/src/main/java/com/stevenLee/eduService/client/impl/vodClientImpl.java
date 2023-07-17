package com.stevenLee.eduService.client.impl;

import com.stevenLee.commonUtils.R;
import com.stevenLee.eduService.client.vodClient;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class vodClientImpl implements vodClient {

    @Override
    public R deleteVideoById(String id) {
        return R.error().message("删除单个视频出错");
    }

    @Override
    public R deleteVideosById(List<String> videoId) {
        return R.error().message("删除多个视频出错");
    }
}
