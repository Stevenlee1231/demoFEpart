package com.stevenLee.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stevenLee.eduService.entity.subject.firstSubject;
import com.stevenLee.eduService.entity.subject.secondSubject;
import com.stevenLee.eduService.listener.subjectExcelListener;
import com.alibaba.excel.EasyExcel;
import com.stevenLee.eduService.entity.EduSubject;
import com.stevenLee.eduService.entity.subjectData;
import com.stevenLee.eduService.mapper.EduSubjectMapper;
import com.stevenLee.eduService.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-07-13
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService ess) {
        try {
            InputStream is = file.getInputStream();
            EasyExcel.read(is, subjectData.class, new subjectExcelListener(ess)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<firstSubject> getAllSubject() {
        //查询一级
        QueryWrapper<EduSubject> qwOne = new QueryWrapper<>();
        List<EduSubject> test = baseMapper.selectList(qwOne);
        qwOne.eq("parent_id", 0);
        List<EduSubject> first = baseMapper.selectList(qwOne);
        //查询二级
        QueryWrapper<EduSubject> qwTw0 = new QueryWrapper<>();
        qwTw0.ne("parent_id", 0);
        List<EduSubject> second = baseMapper.selectList(qwTw0);
        List<firstSubject> result = new ArrayList<>();
        //一级封装
        for (int i = 0; i < first.size(); i++) {
            EduSubject tempE = first.get(i);
            firstSubject temp1 = new firstSubject();
            BeanUtils.copyProperties(tempE, temp1);
            result.add(temp1);
            List<secondSubject> tempSecond = new ArrayList<>();
            for(int j = 0; j < second.size(); j++) {
                EduSubject tempE2 = second.get(j);
                if(tempE2.getParentId().equals(tempE.getId())){
                    secondSubject temp2 = new secondSubject();
                    BeanUtils.copyProperties(tempE2,temp2);
                    tempSecond.add(temp2);
                }
            }
            temp1.setChildren(tempSecond);
        }
        return result;
    }
}
