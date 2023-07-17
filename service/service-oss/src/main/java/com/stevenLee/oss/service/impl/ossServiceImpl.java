package com.stevenLee.oss.service.impl;

import com.aliyun.oss.OSS;
import com.stevenLee.oss.utils.constantPropertiesUtils;
import com.aliyun.oss.OSSClientBuilder;
import com.stevenLee.oss.service.ossService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class ossServiceImpl implements ossService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = constantPropertiesUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = constantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = constantPropertiesUtils.ACCESS_KEY_SECRET;
        // 填写Bucket名称，例如examplebucket。
        String bucketName = constantPropertiesUtils.BUCKET_NAME;
        // 创建OSSClient实例。
        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            InputStream is = file.getInputStream();
            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            fileName = uuid + fileName;
            String time = new DateTime().toString("yyyy/MM/dd");
            fileName = time + "/" + fileName;
            ossClient.putObject(bucketName, fileName, is);
            ossClient.shutdown();
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
