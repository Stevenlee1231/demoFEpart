package com.stevenLee.eduService.controller;

import com.stevenLee.commonUtils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
@RequestMapping("/eduService/edu-user")
public class EduLoginController {
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","admin").data("name","admin").data("avatar","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi.qqkou.com%2Fi%2F0a1035347958x3224715022b15.jpg&refer=http%3A%2F%2Fi.qqkou.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1659881938&t=b3dbaa93860e623018a15db62e2ac6be");
    }
}
