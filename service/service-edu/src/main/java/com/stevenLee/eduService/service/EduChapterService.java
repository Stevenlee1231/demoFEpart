package com.stevenLee.eduService.service;

import com.stevenLee.eduService.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stevenLee.eduService.entity.chapter.chapterVo;
import com.stevenLee.eduService.entity.chapter.videoVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-07-14
 */
public interface EduChapterService extends IService<EduChapter> {
    List<chapterVo> getChapter(String courseId);

    boolean deleteChapter(String chapterId);

    void removeByCourseId(String courseId);
}
