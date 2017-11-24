package com.ccnu.edu.cn;

import java.util.Comparator;

/**
 * Created by 董乐强 on 2017/11/23.
 */
public class SortClass<T> {
    private Comparator<Object> comparator;

    //构造方法传入一个比较器
    public SortClass(Comparator<Object> comparator) {
        this.comparator = comparator;
    }

    public SortClass() {
    }

    /**
     * 插入排序代码实现
     * [0,i-1]区间有序，[i,data.length-1]无序
     * 无序区间不断的插入到有序的区间，有序区间增大到[0,data.length-1],无序区间缩小为空区间的过程
     *
     * @param data 要排序的数组
     */
    public void insertSort(T[] data) {
        //扫描无序区间[i,data.length-1]
        for (int i = 1; i < data.length; i++) {
            //寻找元素i在有序区间[0,i-1]中的插入位置，使得依然为一个有序区间
            for (int j = i - 1; j >= 0; j--) {
                if (_compare(data[j + 1], data[j]) == -1)
                    _swap(data, j + 1, j);
            }
        }
    }

    /**
     * 插入排序
     * 插入排序升级版，该交互为移动，提高效率(交互比移动元素要耗时)
     *
     * @param data
     */
    public void insertSortPromotion(T[] data) {
        //扫描无序区间[i,data.length-1]
        for (int i = 1; i < data.length; i++) {
            //寻找元素i在有序区间[0,i-1]中的插入位置，使得依然为一个有序区间
            int j = i - 1;
            T temp = data[i];
            for (; j >= 0; j--)
                if (_compare(temp, data[j]) == -1)
                    data[j + 1] = data[j];
                else
                    break;
            data[j + 1] = temp;
        }
    }

    /**
     * 选择排序代码实现
     * 有序区间[0,i-1],无序区间[i,data.length-1]
     * 每次都在扫描无序区间[i,data.length-1]寻找一个最小值与有序区间的最后一个元素i-1进行交换，从而
     * 有序区间增大到[0,data.length-1],无序区间缩小为空区间的过程
     *
     * @param data
     */
    public void selectSort(T[] data) {
        for (int i = 0; i < data.length; i++) {
            //在[i,n)选出最小值
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (_compare(data[minIndex], data[j]) == 1)
                    minIndex = j;
            }
            //选出的最小值与区间最左边的i进行交换
            _swap(data, i, minIndex);
        }
    }

    /**
     * 归并排序算法递归实现(二路归并排序)
     *
     * @param data
     */
    public void mergeSort(T[] data) {
        int left = 0;
        int right = data.length - 1;
        _mergeSort(data, left, right);
    }

    /**
     * 归并排序算法，非递归实现(二路归并排序)
     * @param data
     */
    public void mergeSortPromotion(T[] data){
           int maxSize = data.length%2==0?data.length/2:data.length-1;
           for(int size=1;size<=maxSize;size*=2){
               //[i,i+size-1] [i+size,i+size+size-1],平移下标，进行归并
                for(int i =0 ; i+size<data.length;i+=2*size)
                     //注意这一步的优化
                     if(_compare(data[i+size-1],data[i+size])==1)
                     _merge(data,i,i+size-1, (Integer) min(i+2*size-1,data.length-1));
           }
    }

    private void _mergeSort(T[] data, int left, int right) {
        if (left == right)
            return;
        int mid = (left + right) / 2;
        //归并左边的元素
        _mergeSort(data, left, mid);
        //归并右边的元素
        _mergeSort(data, mid + 1, right);
        //进行合并
        if(_compare(data[mid],data[mid+1])==1)
        _merge(data, left, mid, right);
    }

    /**
     * 将有序区间[left,mid]和[mid+1,right]合并后，使得区间[left,right]任然有序
     *
     * @param data
     * @param left
     * @param mid
     * @param right
     */
    private void _merge(T[] data, int left, int mid, int right) {
        //辅助数组
        Object[] temp_data = new Object[right - left + 1];
        //将区间[left,right]的元素复制到temp_data;
        for (int i = left; i <= right; i++)
            temp_data[i - left] = data[i];
        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            //注意，必须先进行判断i>mid和j>right，否则会造成数组越界的问题
            if(i>mid){
                data[k] = (T) temp_data[j-left];
                j++;
            }else if(j>right){
                data[k] = (T) temp_data[i-left];
                i++;
            }
            else if (_compare(temp_data[i-left], temp_data[j-left]) == -1 || _compare(temp_data[i-left], temp_data[j-left]) == 0) {
                data[k] = (T) temp_data[i-left];
                i++;
            }
            else if (_compare(temp_data[i-left], temp_data[j-left]) == 1) {
                data[k] = (T) temp_data[j-left];
                j++;
            }
        }
    }

    //用于比较两个数的大小
    private int _compare(Object source, Object target) {
        if (comparator != null)
            return comparator.compare(source, target);
        Comparable<Object> comparable = (Comparable<Object>) source;
        return comparable.compareTo(target);
    }
    //交换数组的两个下标
    private void _swap(T[] data, int source, int target) {
        T temp = data[source];
        data[source] = data[target];
        data[target] = temp;
    }
    //求两个数的最小值
    private Object min(Object source,Object target){
        if(_compare(source,target)==-1){
            return source;
        }
        return target;
    }
}
