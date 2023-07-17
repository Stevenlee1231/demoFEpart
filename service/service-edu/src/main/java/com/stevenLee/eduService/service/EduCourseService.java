package com.stevenLee.eduService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stevenLee.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stevenLee.eduService.entity.EduTeacher;
import com.stevenLee.eduService.entity.chapter.videoVo;
import com.stevenLee.eduService.entity.courseQuery;
import com.stevenLee.eduService.entity.teacherQuery;
import com.stevenLee.eduService.entity.vo.courseVo;
import com.stevenLee.eduService.entity.vo.front.courseQueryVo;
import com.stevenLee.eduService.entity.vo.front.courseWebVo;
import com.stevenLee.eduService.entity.vo.publishVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-07-14
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseVo(courseVo cv);

    courseVo getCourse(String id);

    void updateCourse(courseVo cv);

    publishVo getPublish(String courseId);

    void pageQuery(Page<EduCourse> pageParam, courseQuery cq);

    void deleteCourse(String courseId);

    Map<String, Object> pageList(Page<EduCourse> pageParam, courseQueryVo qrv);

    courseWebVo getInfo(String courseId);
}
