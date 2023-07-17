package com.stevenLee.eduService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stevenLee.commonUtils.R;
import com.stevenLee.config.exceptionHandler.guliException;
import com.stevenLee.eduService.entity.EduTeacher;
import com.stevenLee.eduService.entity.teacherQuery;
import com.stevenLee.eduService.service.EduTeacherService;
import com.sun.corba.se.spi.resolver.Resolver;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-07-02
 */
@CrossOrigin(origins="*",maxAge=3600)
@RestController
@RequestMapping("/eduService/edu-teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService ets;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping
    public R list() {
        List<EduTeacher> list = ets.list(null);
        return R.ok().data("items", list);
    }

    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeById(@ApiParam(name = "id", value = "讲师ID", required = true)
                        @PathVariable String id) {
        ets.removeById(id);
        return R.ok();
    }
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("pageList/{page}/{limit}")
    public R pageList(
            @PathVariable Long page,
            @PathVariable Long limit){
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        ets.page(pageParam, null);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }
    @PostMapping("pageQuery/{page}/{limit}")
    public R pageQuery(@PathVariable Long page, @PathVariable Long limit,@RequestBody(required = false) teacherQuery tq){
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        QueryWrapper qw = new QueryWrapper();
        Integer level = tq.getLevel();
        String name = tq.getName();
        String begin = tq.getBegin();
        String end = tq.getEnd();
        if (!StringUtils.isEmpty(name)) {
            qw.like("name", name);
        }
        if (!StringUtils.isEmpty(level) ) {
            qw.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            qw.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            qw.le("gmt_create", end);
        }
        ets.page(pageParam, qw);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }
    @ApiOperation(value="新增讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher et){
        boolean flag = ets.save(et);
        if(flag)
            return R.ok();
        else
            return R.error();
    }
    @ApiOperation(value="根据ID查询讲师")
    @GetMapping("idQuery/{id}")
    public R idQuery(@PathVariable String id){
        EduTeacher teacher = ets.getById(id);
        return R.ok().data("teacher",teacher);
    }
    @ApiOperation(value="根据ID修改讲师")
    @PostMapping("idUpdate")
    public R idUpdate(@RequestBody EduTeacher et){
        boolean flag = ets.updateById(et);
        if(flag)
            return R.ok();
        else
            return R.error();
    }
}


