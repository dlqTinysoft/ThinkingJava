package com.imooc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by 董乐强 on 2017/12/13.
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    /**
     * 公众平台id
     */
    private String mpAppId;
    /**
     * 公众平台秘钥
     */
    private String mpAppSecret;

    private String openAppId;
    private String openAppSecret;

    //商户号
    private String mchId;
    //证书
    private String mchKey;
    //路径
    private String keyPath;
    //微信支付异步通知
    private String notifyUrl;

}
