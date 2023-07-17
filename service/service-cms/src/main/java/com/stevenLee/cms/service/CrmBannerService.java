package com.stevenLee.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stevenLee.cms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-07-24
 */
public interface CrmBannerService extends IService<CrmBanner> {

    void pageBanner(Page<CrmBanner> pageParam, Object o);

    List<CrmBanner> getBanner();
}
