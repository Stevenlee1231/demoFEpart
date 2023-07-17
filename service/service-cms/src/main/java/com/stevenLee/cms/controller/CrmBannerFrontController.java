package com.stevenLee.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stevenLee.cms.entity.CrmBanner;
import com.stevenLee.cms.service.CrmBannerService;
import com.stevenLee.commonUtils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduCms/bannerFront")
@CrossOrigin
public class CrmBannerFrontController {
    @Autowired
    private CrmBannerService cbs;
    @GetMapping("getBanner")
    public R getBanner(){
        List<CrmBanner> list = cbs.getBanner();
        return R.ok().data("list",list);
    }
}
