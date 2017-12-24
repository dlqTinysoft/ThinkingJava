package com.ccnu.edu.cn;

/**
 * Created by 董乐强 on 2017/12/7.
 */
public interface SearchClass<T> {
    //二分搜索data中是否有target,如果有则返回target的在data中的下标，如果没有则返回-1
    //非递归
    int binarySearch(T [] data , T target);
    //递归
    int binarySearch1(T [] data , T target);
}
