package com.stevenLee.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stevenLee.cms.entity.CrmBanner;
import com.stevenLee.cms.mapper.CrmBannerMapper;
import com.stevenLee.cms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-07-24
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override

    public void pageBanner(Page<CrmBanner> pageParam, Object o) {

    }

    @Override
    @Cacheable(value="bannerList",key="'banner'" )
    public List<CrmBanner> getBanner() {
        QueryWrapper<CrmBanner> qw = new QueryWrapper<>();
        qw.orderByDesc("id");
        qw.last("limit 2");
        List<CrmBanner> result = baseMapper.selectList(null);
        return result;
    }
}
