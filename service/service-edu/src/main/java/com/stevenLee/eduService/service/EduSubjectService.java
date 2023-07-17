package com.stevenLee.eduService.service;

import com.stevenLee.eduService.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stevenLee.eduService.entity.subject.firstSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-07-13
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService ess);


    List<firstSubject> getAllSubject();
}
