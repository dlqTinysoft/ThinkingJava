package com.imooc.exception;

import com.imooc.eunm.ResultEnum;

/**
 * Created by 董乐强 on 2017/12/10.
 * 统一异常管理
 */
public class SellException extends RuntimeException {
    private Integer code;
    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
    public SellException(Integer code , String message){
        super(message);
        this.code = code;
    }

}
