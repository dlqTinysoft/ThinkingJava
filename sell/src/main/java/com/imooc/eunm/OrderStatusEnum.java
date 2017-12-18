package com.imooc.eunm;

import com.sun.xml.internal.bind.v2.model.core.EnumConstant;
import lombok.Data;
import lombok.Getter;

/**
 * Created by 董乐强 on 2017/12/9.
 */
@Getter
public enum OrderStatusEnum implements CodeEnum{
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"取消"),
    ;
    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }





}
