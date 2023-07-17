package com.stevenLee.eduService.service;

import com.stevenLee.eduService.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-07-14
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeByCourseId(String courseId);

}
