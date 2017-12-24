package com.imooc.utils;

import com.imooc.eunm.CodeEnum;

/**
 * Created by 董乐强 on 2017/12/14.
 */
public class EnumUtil {
    //这个工具类，写的非常有水平，认真看一下
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
            //通过code来得到该枚举
             for(T each:enumClass.getEnumConstants()){
                 if(code.equals(each.getCode())){
                     return each;
                 }
             }
             return null;
    }
}
