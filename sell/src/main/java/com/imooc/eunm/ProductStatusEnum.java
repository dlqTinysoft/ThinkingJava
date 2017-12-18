package com.imooc.eunm;

import lombok.Getter;

/**
 * Created by 董乐强 on 2017/12/8.
 */
@Getter//使用的lomback这个小工具
public enum ProductStatusEnum implements  CodeEnum {
    UP(0,"在架"),
    DOWN(1,"下架");
    private Integer code;
    private String message;
    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
