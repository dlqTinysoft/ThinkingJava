package com.imooc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by 董乐强 on 2017/12/12.
 *
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {
     @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){

         log.info("code={}",code);
         log.info("进入auth方法。。。。。");
         String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx7ee875b1e7ba2530&secret=20df1336c240c0a2ab37451b9d14f66c&code="+code+"&grant_type=authorization_code";

         RestTemplate restTemplate = new RestTemplate();
         String response = restTemplate.getForObject(url,String.class);
         log.info("response={}",response);


     }
}
