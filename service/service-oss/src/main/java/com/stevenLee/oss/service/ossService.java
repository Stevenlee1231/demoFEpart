package com.stevenLee.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface ossService {
    String uploadFileAvatar(MultipartFile file) throws FileNotFoundException;
}
