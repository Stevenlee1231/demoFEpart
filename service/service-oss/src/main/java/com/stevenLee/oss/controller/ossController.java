package com.stevenLee.oss.controller;

import com.stevenLee.commonUtils.R;
import com.stevenLee.oss.service.ossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/eduOss")
@CrossOrigin
public class ossController {
    @Autowired
    private ossService os;
    @PostMapping()
    public R uploadOssFile(MultipartFile file) throws FileNotFoundException {
        String url = os.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
