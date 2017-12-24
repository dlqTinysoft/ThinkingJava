package com.ccnu.edu.cn;

/**
 * Created by 董乐强 on 2017/12/7.
 */
public class SearchClassImpl<T> extends SortClassImpl<T> implements SearchClass<T> {

    /**
     * 二分查找，必须元素必须是有序的
     * 非递归
     * @param data
     * @param target
     * @return
     */
    @Override
    public int binarySearch(T[] data, T target) {
        //[left,right]这个区间
        int left = 0;
        int right = data.length - 1;
        //[left,right] 不为空时，则结束，即right<=right的情况
        while (left <= right) {
            //获得[left,right]区间的中点
            int middle = left + (right - left) / 2;
            T middleData = data[middle];
            //[left,middle-1]
            if (_compare(target, middleData) == -1)
                right = middle - 1;
            else if (_compare(target, middleData) == 1)
                left = middle + 1;
            else
                return middle;
        }
        return -1;
    }

    //二分查找，递归实现
    public int binarySearch1(T [] data, T target){
        return _binarySearch(0,data.length-1,data,target);
    }

    private int  _binarySearch(int left, int right,T[]data, T target){
        int middle = left+(right - left)/2;
        //递归结束条件
        if(left>right)
            return -1;
        if(_compare(target,data[middle])==-1)
            return _binarySearch(left,middle-1,data,target);
        if(_compare(target,data[middle])==1)
            return _binarySearch(middle+1,right,data,target);
        return middle;
    }
}
