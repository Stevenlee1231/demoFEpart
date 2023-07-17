package com.stevenLee.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stevenLee.cms.entity.CrmBanner;
import com.stevenLee.cms.service.CrmBannerService;
import com.stevenLee.commonUtils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-07-24
 */
@RestController
@RequestMapping("/eduCms/bannerAdmin")
@CrossOrigin
public class CrmBannerController {
    @Autowired
    private CrmBannerService cbs;
    @GetMapping("{page}/{limit}")
    public R index(@PathVariable Long page,@PathVariable Long limit){
        Page<CrmBanner> pageParam = new Page<>(page, limit);
        cbs.pageBanner(pageParam,null);
        return R.ok().data("items", pageParam.getRecords()).data("total",pageParam.getTotal());
    }
    //获取
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner cb){
        cbs.save(cb);
        return R.ok();
    }
    @PutMapping("Update")
    public R updateBanner(@RequestBody CrmBanner banner) {
        cbs.updateById(banner);
        return R.ok();
    }
    @DeleteMapping("remove/{id}")
    public R deleteById(@PathVariable String id) {
        cbs.removeById(id);
        return R.ok();
    }
}

