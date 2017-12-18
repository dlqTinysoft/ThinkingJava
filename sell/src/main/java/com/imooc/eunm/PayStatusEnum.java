package com.imooc.eunm;

import lombok.Getter;

/**
 * Created by 董乐强 on 2017/12/9.
 */
@Getter
public enum PayStatusEnum implements CodeEnum {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),
    ;
    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}

