package com.imooc.utils;

import javax.xml.crypto.Data;

/**
 * Created by 董乐强 on 2017/12/13.
 */
public class MathUtils {
    private static final Double Money_Range=0.01;
    public static Boolean equals(Double d1, Double d2){
        Double result = Math.abs(d1-d2);
        if(result<Money_Range)
            return true;
        return false;
    }
}
