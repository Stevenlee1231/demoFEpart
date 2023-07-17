package com.stevenLee.eduService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stevenLee.eduService.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stevenLee.eduService.entity.teacherQuery;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-07-02
 */
public interface EduTeacherService extends IService<EduTeacher> {
    Map<String, Object> pageList(Page<EduTeacher> pageParam);
}
interface teacherService extends IService<EduTeacher> {
    void pageQuery(Page<EduTeacher> pageParam, teacherQuery teacherQuery);
}
