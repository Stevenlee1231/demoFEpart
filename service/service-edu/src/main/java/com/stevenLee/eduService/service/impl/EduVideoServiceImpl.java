package com.stevenLee.eduService.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stevenLee.eduService.client.vodClient;
import com.stevenLee.eduService.entity.EduVideo;
import com.stevenLee.eduService.mapper.EduVideoMapper;
import com.stevenLee.eduService.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-07-14
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    @Autowired
        private vodClient vc;
    @Override
    public void removeByCourseId(String courseId) {
        QueryWrapper<EduVideo> qw = new QueryWrapper<>();
        qw.eq("course_id",courseId);
        qw.select("video_source_id");
        List<EduVideo> list = baseMapper.selectList(qw);
        List<String> list2 = new ArrayList<>();
        for(EduVideo ev : list) {
            String s = ev.getVideoSourceId();
            if (!StringUtils.isEmpty(s))
                list2.add(s);
        }
        System.out.println(list2);
        if(list2.size()>0)
            vc.deleteVideosById(list2);
        QueryWrapper<EduVideo> qw2 = new QueryWrapper<>();
        qw2.eq("course_id",courseId);
        baseMapper.delete(qw2);
    }

}
