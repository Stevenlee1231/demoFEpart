package com.stevenLee.eduUcenter.service;

import com.stevenLee.eduUcenter.entity.LoginVo;
import com.stevenLee.eduUcenter.entity.RegisterVo;
import com.stevenLee.eduUcenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-08-10
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(LoginVo lv);

    void register(RegisterVo rv);
}
