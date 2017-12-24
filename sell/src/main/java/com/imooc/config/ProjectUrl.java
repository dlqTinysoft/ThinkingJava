package com.imooc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by 董乐强 on 2017/12/21.
 */
@Data
@ConfigurationProperties(prefix = "projectUrl")
@Component
public class ProjectUrl {
    /**
     * 微信公众平台授权url
     */
    public String wechatMpAuthorize;
    public String wechatOpenAuthorize;

    /**
     * 点餐系统
     */
    public String sell;
    //
    private String dlq;
}
