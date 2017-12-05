package com.ccnu.edu.cn;

/**
 * Created by 董乐强 on 2017/11/26.
 */
public interface SortClass<T> {
    //单路快速排序
    void speedSort(T[] data);
    //二路快速排序
    void speedSort1(T[] data);
    //三路快速排序
    void speedSort2(T[] data);
    //插入排序(交换)
    void insertSort(T[] data);
    //插入排序(移动)
    void insertSortPromotion(T [] data);
    //选择排序
    void selectSort(T[] data);
    //归并排序(递归)
    void mergeSort(T[] data);
    //归并排序(非递归)
    void mergeSortPromotion(T[] data);
    //堆排序算法
    void HeapSort(T[] data);

}
