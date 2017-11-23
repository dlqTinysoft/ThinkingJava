package com.ccnu.edu.cn;

import java.util.Comparator;

/**
 * Created by 董乐强 on 2017/11/23.
 */
public class SortClass<T> {
    private Comparator<T> comparator;
    //构造方法传入一个比较器
    public SortClass(Comparator<T> comparator){
        this.comparator=comparator;
    }
    public SortClass(){
    }

    /**插入排序代码实现
     * [0,i-1]区间有序，[i,data.length-1]无序
     * 无序区间不断的插入到有序的区间，有序区间增大到[0,data.length-1],无序区间缩小为空区间的过程
     * @param data 要排序的数组
     */
    public  void insertSort(T [] data){
        //扫描无序区间[i,data.length-1]
        for(int i =1; i<data.length;i++){
            //寻找元素i在有序区间[0,i-1]中的插入位置，使得依然为一个有序区间
            for(int j =i-1;j>=0;j--){
               if(_compare(data[j+1],data[j])==-1)
                   _swap(data,j+1,j);
            }
        }
    }

    /**插入排序
     * 插入排序升级版，该交互为移动，提高效率(交互比移动元素要耗时)
     * @param data
     */
    public void insertSortPromotion(T [] data){
        //扫描无序区间[i,data.length-1]
        for(int i =1; i<data.length;i++){
            //寻找元素i在有序区间[0,i-1]中的插入位置，使得依然为一个有序区间
            int j =i-1;
            T temp = data[i];
            for(;j>=0;j--)
                if(_compare(temp,data[j])==-1)
                    data[j+1]=data[j];
                else
                    break;
            data[j+1]=temp;
        }
    }

    /**选择排序代码实现
     * 有序区间[0,i-1],无序区间[i,data.length-1]
     * 每次都在扫描无序区间[i,data.length-1]寻找一个最小值与有序区间的最后一个元素i-1进行交换，从而
     * 有序区间增大到[0,data.length-1],无序区间缩小为空区间的过程
     * @param data
     */
    public void selectSort(T [] data){
        for(int i =0 ; i<data.length;i++){
            //在[i,n)选出最小值
            int minIndex = i;
            for(int j = i+1;j<data.length;j++){
                if( _compare(data[minIndex],data[j])==1)
                    minIndex=j;
            }
            //选出的最小值与区间最左边的i进行交换
            _swap(data,i,minIndex);
        }
    }

    /**归并排序算法实现
     *
     * @param data
     */
    public void mergeSort(T [] data){

    }



















    //用于比较两个数的大小
    private int _compare(T source,T target){
        if(comparator!=null)
            return comparator.compare(source,target);
        Comparable<T> comparable = (Comparable<T>) source;
        return comparable.compareTo(target);
    }

    private void _swap(T[] data,int source,int target){
        T temp = data[source];
        data[source]=data[target];
        data[target]=temp;
    }
}
