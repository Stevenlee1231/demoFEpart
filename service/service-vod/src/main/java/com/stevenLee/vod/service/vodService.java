package com.stevenLee.vod.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface vodService {
    String uploadVideo(MultipartFile file);

    void deleteVideosById(List<String> list);
}
