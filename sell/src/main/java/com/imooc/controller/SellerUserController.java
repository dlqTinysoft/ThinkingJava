package com.imooc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 董乐强 on 2017/12/21.
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {
    @GetMapping("/login")
    public void login(){
        //1.openId去和数据库里的数据匹配
        //2.设置token至redis
        //3.设置token到cookie
    }

    @GetMapping("/logout")
    public void logout(){

    }
}
