package com.imooc.utils;

import java.util.Random;

/**
 * Created by 董乐强 on 2017/12/11.
 */
public class KeyUtil {
    /**
     * 生产唯一的主键
     * 格式：时间+随机数
     * 必须加上同步
     * @return
     */
    public static synchronized String genUniqueKey(){
       Random random = new Random();
       Integer number = random.nextInt(900000)+100000;
       return System.currentTimeMillis()+String.valueOf(number);
    }
}
