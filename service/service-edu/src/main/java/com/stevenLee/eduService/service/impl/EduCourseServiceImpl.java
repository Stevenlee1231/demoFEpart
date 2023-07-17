package com.stevenLee.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stevenLee.config.exceptionHandler.guliException;
import com.stevenLee.eduService.entity.EduCourse;
import com.stevenLee.eduService.entity.EduCourseDescription;
import com.stevenLee.eduService.entity.EduSubject;
import com.stevenLee.eduService.entity.chapter.videoVo;
import com.stevenLee.eduService.entity.courseQuery;
import com.stevenLee.eduService.entity.vo.courseVo;
import com.stevenLee.eduService.entity.vo.front.courseQueryVo;
import com.stevenLee.eduService.entity.vo.front.courseWebVo;
import com.stevenLee.eduService.entity.vo.publishVo;
import com.stevenLee.eduService.mapper.EduCourseMapper;
import com.stevenLee.eduService.service.EduChapterService;
import com.stevenLee.eduService.service.EduCourseDescriptionService;
import com.stevenLee.eduService.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stevenLee.eduService.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-07-14
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eds;
    @Autowired
    private EduVideoService evs;
    @Autowired
    private EduChapterService ecs;

    @Override

    public String saveCourseVo(courseVo cv) {
        EduCourse ec = new EduCourse();
        BeanUtils.copyProperties(cv, ec);
        int i = baseMapper.insert(ec);
        if (i == 0) {
            throw new guliException("添加失败", 20001);
        }
        String cid = ec.getId();
        //添加简介表
        EduCourseDescription ecd = new EduCourseDescription();
        ecd.setDescription(cv.getDescription());
        ecd.setId(cid);
        eds.save(ecd);
        return cid;
    }

    //根据ID查询课程
    @Override
    public courseVo getCourse(String courseId) {
        //
        EduCourse ec = baseMapper.selectById(courseId);
        EduCourseDescription ecd = eds.getById(courseId);
        courseVo cv = new courseVo();
        BeanUtils.copyProperties(ec, cv);
        cv.setDescription(ecd.getDescription());
        return cv;
    }

    @Override
    public void updateCourse(courseVo cv) {
        //修改课程
        EduCourse ec = new EduCourse();
        BeanUtils.copyProperties(cv, ec);
        int i1 = baseMapper.updateById(ec);
        if (i1 == 0) {
            throw new guliException("修改失败", 20001);
        }
        //修改课程描述
        EduCourseDescription ecd = new EduCourseDescription();
        BeanUtils.copyProperties(cv, ecd);
        eds.updateById(ecd);
    }

    @Override
    public publishVo getPublish(String courseId) {
        publishVo pv = baseMapper.getPublish(courseId);
        return pv;
    }

    @Override
    public void pageQuery(Page<EduCourse> pageParam, courseQuery cq) {
        QueryWrapper<EduCourse> qw = new QueryWrapper<>();
        qw.orderByDesc("gmt_create");
        if (cq == null) {
            baseMapper.selectPage(pageParam, qw);
            return;
        }
        String title = cq.getTitle();
        String status = cq.getStatus();
        if (!StringUtils.isEmpty(title)) {
            qw.like("title", title);
        }
        if (!StringUtils.isEmpty(status)) {
            qw.eq("status", status);
        }
        baseMapper.selectPage(pageParam, qw);
    }

    @Override
    public void deleteCourse(String courseId) {
        //删小节
        evs.removeByCourseId(courseId);
        //删章节
        ecs.removeByCourseId(courseId);
        //删描述信息
        eds.removeById(courseId);
        //删课程
        int i = baseMapper.deleteById(courseId);
        if (i == 0) {
            throw new guliException("删除失败", 20001);
        }
    }

    @Override
    public Map<String, Object> pageList(Page<EduCourse> pageParam, courseQueryVo cqv) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(cqv.getSubjectParentId())) {
            queryWrapper.eq("subject_parent_id",
                    cqv.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(cqv.getSubjectId())) {
            queryWrapper.eq("subject_id", cqv.getSubjectId());
        }
        if (!StringUtils.isEmpty(cqv.getBuyCountSort())) {
            queryWrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(cqv.getGmtCreateSort())) {
            queryWrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(cqv.getPriceSort())) {
            queryWrapper.orderByDesc("price");
        }
        baseMapper.selectPage(pageParam, queryWrapper);
        List<EduCourse> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }

    @Override
    public courseWebVo getInfo(String courseId)
    {
        return baseMapper.getCourseInfo(courseId);
    }
}
