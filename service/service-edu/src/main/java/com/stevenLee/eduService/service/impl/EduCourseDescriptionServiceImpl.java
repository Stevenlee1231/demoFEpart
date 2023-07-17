package com.stevenLee.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stevenLee.eduService.entity.EduChapter;
import com.stevenLee.eduService.entity.EduCourseDescription;
import com.stevenLee.eduService.mapper.EduCourseDescriptionMapper;
import com.stevenLee.eduService.service.EduCourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-07-14
 */
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {

    @Override
    public void removeByCourseId(String courseId) {
        QueryWrapper<EduCourseDescription> qw = new QueryWrapper<>();
        qw.eq("course_id",courseId);
        baseMapper.delete(qw);
    }
}
