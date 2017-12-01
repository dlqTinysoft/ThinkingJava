package com.ccnu.edu.cn;

import java.util.Comparator;

/**
 * Created by 董乐强 on 2017/11/30.
 * 最大堆的实现
 */
public class MaxHeap<T> {
    //堆的容量
    private final static int capacity = 17;
    //堆中元素的个数
    private int count;
    //用于存储堆中的元素
    private Object[] objects;
    //定义一个比较器
    private Comparator<Object> comparator;

    public MaxHeap() {
        objects = new Object[MaxHeap.capacity];
    }

    public MaxHeap(int capacity) {
        //这里面，要进行加1的原因是，存储的元素是从1开始的
        objects = new Object[capacity + 1];
    }

    //传入一个比较器
    public MaxHeap(int capacity, Comparator<Object> comparator) {
        objects = new Object[capacity + 1];
        this.comparator = comparator;
    }

    //像堆中插入一个元素
    public void insert(T data) {
        //如果元素个数大于capacity则抛出异常
        if (count > capacity)
            throw new RuntimeException("Heap is full,not insert !");
        //从数组的索引1开始加入元素
        objects[++count] = data;
        //插入之后，要对堆，进行调整
        shiftUp(count);
    }

    //遍历堆中所有元素



    //来调整堆，从下往上的进行调整
    private void shiftUp(int position) {
        //得到其父节点的位置
        int parent = position / 2;
        //一定要将parent>1写在判断前面，可以加快效率，还有一点就是当加入一个元素时，如果先判断比较的话，会出现null异常，因为1/2=0 ,但是0位置为null
        while ( parent > 1 && _compare(position, parent) == 1 ) {
            _swap(position, parent);
            parent = parent / 2;
        }
    }

    private int _compare(int source, int target) {
        if (comparator != null)
            return comparator.compare(objects[source], objects[target]);
        Comparable comparable = (Comparable) objects[source];
        return comparable.compareTo(objects[target]);
    }

    private void _swap(int source, int target) {
        Object temp = objects[source];
        objects[source] = objects[target];
        objects[target] = temp;
    }
}
