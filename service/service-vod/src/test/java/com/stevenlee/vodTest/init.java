package com.stevenlee.vodTest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.netflix.client.ClientException;

public class init {
    public static DefaultAcsClient initVodClient(String accessKeyId, String
            accessKeySecret) throws ClientException {
        String regionId = "cn-shanghai"; // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId,
                accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
