package com.stevenLee.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stevenLee.config.exceptionHandler.guliException;
import com.stevenLee.eduService.entity.EduChapter;
import com.stevenLee.eduService.entity.EduSubject;
import com.stevenLee.eduService.entity.EduVideo;
import com.stevenLee.eduService.entity.chapter.chapterVo;
import com.stevenLee.eduService.entity.chapter.videoVo;
import com.stevenLee.eduService.entity.subject.firstSubject;
import com.stevenLee.eduService.entity.subject.secondSubject;
import com.stevenLee.eduService.mapper.EduChapterMapper;
import com.stevenLee.eduService.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stevenLee.eduService.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-07-14
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService evs;
    @Override
    public List<chapterVo> getChapter(String courseId) {
        //查询章节
        QueryWrapper<EduChapter> qwFirst = new QueryWrapper<>();
        qwFirst.eq("course_id", courseId);
        List<EduChapter> first = baseMapper.selectList(qwFirst);
        //查询小节
        QueryWrapper<EduVideo> qwSecond = new QueryWrapper<>();
        qwSecond.eq("course_id", courseId);
        List<EduVideo> second = evs.list(qwSecond);
        List<chapterVo> result = new ArrayList<>();
        for(int i = 0; i < first.size(); i++){
            EduChapter tempEc = first.get(i);
            chapterVo cv = new chapterVo();
            BeanUtils.copyProperties(tempEc,cv);
            result.add(cv);
            List<videoVo> tempSecond = new ArrayList<>();
            for (int j = 0; j < second.size(); j++){
                EduVideo tempEv = second.get(j);
                if(tempEv.getChapterId().equals(tempEc.getId())) {
                    videoVo vv = new videoVo();
                    BeanUtils.copyProperties(tempEv, vv);
                    tempSecond.add(vv);
                }
            }
            cv.setChildren(tempSecond);
        }
        return result;
    }
    //删除章节
    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> qwSecond = new QueryWrapper<>();
        qwSecond.eq("chapter_id", chapterId);
        int count  = evs.count(qwSecond);
        if (count == 0){
            int i = baseMapper.deleteById(chapterId);
            return i > 0;
        }
        else
            throw new guliException("删除失败",20001);
    }

    @Override
    public void removeByCourseId(String courseId) {
        QueryWrapper<EduChapter> qw = new QueryWrapper<>();
        qw.eq("course_id",courseId);
        baseMapper.delete(qw);
    }
}
