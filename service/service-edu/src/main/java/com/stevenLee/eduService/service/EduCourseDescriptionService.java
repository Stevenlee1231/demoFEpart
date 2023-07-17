package com.stevenLee.eduService.service;

import com.stevenLee.eduService.entity.EduCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-07-14
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {

    void removeByCourseId(String courseId);
}
