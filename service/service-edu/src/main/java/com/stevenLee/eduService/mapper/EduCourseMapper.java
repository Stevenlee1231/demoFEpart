package com.stevenLee.eduService.mapper;

import com.stevenLee.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stevenLee.eduService.entity.vo.front.courseWebVo;
import com.stevenLee.eduService.entity.vo.publishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-07-14
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public publishVo getPublish(String courseId);

    public courseWebVo getCourseInfo(String courseId);
}
