package com.stevenLee.eduUcenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stevenLee.commonUtils.R;
import com.stevenLee.commonUtils.jwtUtils;
import com.stevenLee.eduUcenter.entity.LoginVo;
import com.stevenLee.eduUcenter.entity.RegisterVo;
import com.stevenLee.eduUcenter.entity.UcenterMember;
import com.stevenLee.eduUcenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-08-10
 */
@RestController
@RequestMapping("/eduUcenter")
@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService ums;
    @PostMapping("login")
    public R login(@RequestBody LoginVo lv){
        String token = ums.login(lv);
        return R.ok().data("token",token);
    }
    @PostMapping("register")
    public R register(@RequestBody RegisterVo rv){
        ums.register(rv);
        return R.ok();
    }
    @GetMapping("getInfo")
    public R getInfo(HttpServletRequest request){
        String id = jwtUtils.getMemberIdByJwtToken(request);
        UcenterMember um = ums.getById(id);
        return R.ok().data("um",um);
    }

}

