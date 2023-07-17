package com.stevenLee.eduUcenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stevenLee.config.exceptionHandler.guliException;
import com.stevenLee.eduUcenter.entity.LoginVo;
import com.stevenLee.eduUcenter.entity.RegisterVo;
import com.stevenLee.eduUcenter.entity.UcenterMember;
import com.stevenLee.eduUcenter.mapper.UcenterMemberMapper;
import com.stevenLee.eduUcenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.stevenLee.commonUtils.jwtUtils;
import com.stevenLee.commonUtils.MD5;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-08-10
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    @Override
    public String login(LoginVo lv) {
        String mobile = lv.getMobile();
        String password = lv.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password))
            throw new guliException("登陆失败",20001);
        QueryWrapper<UcenterMember> um1 = new QueryWrapper<>();
        um1.eq("mobile",mobile);
        UcenterMember um = baseMapper.selectOne(um1);
        if(um == null)
            throw new guliException("登陆失败",20001);
        if(!MD5.encrypt(password).equals(um.getPassword()))
            throw new guliException("登陆失败",20001);
        if (um.getIsDisabled())
            throw new guliException("登陆失败",20001);
        String token = jwtUtils.getJwtToken(um.getId(),um.getNickname());
        return token;
    }

    @Override
    public void register(RegisterVo rv) {
        String nickname = rv.getNickname();
        String mobile = rv.getMobile();
        String password = rv.getPassword();
        if(StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password)) {
            throw new guliException("注册失败",20001);
        }
        QueryWrapper<UcenterMember> qw = new QueryWrapper<>();
        qw.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(qw);
        if(count>0)
            throw new guliException("注册失败",20001);
        UcenterMember um = new UcenterMember();
        um.setMobile(mobile);
        um.setNickname(nickname);
        um.setPassword(MD5.encrypt(password));
        um.setIsDisabled(false);
        um.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        this.save(um);
    }
}
