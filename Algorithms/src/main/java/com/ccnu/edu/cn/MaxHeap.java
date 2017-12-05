package com.ccnu.edu.cn;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by 董乐强 on 2017/11/30.
 * 最大堆的实现
 */
public class MaxHeap<T> {
    //堆的容量
    private final static int capacity = 16;
    //堆中元素的个数
    private int count;
    //用于存储堆中的元素
    private Object[] objects;
    //定义一个比较器
    private Comparator<Object> comparator;

    public MaxHeap() {
        objects = new Object[MaxHeap.capacity + 1];
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

    //插入一个数组，使这个数组满足堆的性质
    public MaxHeap(T [] data){
         objects = data;
         count = objects.length;
         for(int i =count/2-1 ; i>=0 ;i--)
             //进行向下调整，数组下标是从0开始的，不是从1开始的向下调整
             shiftDown1(i);
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

    //得到堆顶元素,如果堆中没有元素，则返回null
    public T getMaxHeadTop() {
        T data = null;
        if (count!=-1) {
            //向下调整
            data = (T) objects[0];
            _swap(0, --count);
            shiftDown1(0);
            return data;
        }
        return data;
    }

    //遍历堆中所有元素,返回的是一个内部类对象,即返回一个迭代器对象
    public Iterator<T> iterator() {
        return new Itr();
    }

    //得到堆中的元素个数
    public int count(){
        return count;
    }

    //数组下标从1开始，往下调整
    private void shiftDown(int position) {
        int leftChild = 2 * position;
        int rightChild = 2 * position + 1;
        int j = leftChild;
        while (j<=count) {
            //j指向position最大的一个孩子
            //注意这个地方必须要进行判断，rightChild要小于count才可以，要不然左孩子有可能不存在
            if (rightChild<=count && _compare(j, rightChild) == -1)
                j = rightChild;

            if (_compare(position, j) == -1)
                _swap(position, j);
            else
                break;
            //往下扫描
            position = j;
            //j始终指向leftChild
            j = 2 * position;
            rightChild = 2 * position + 1;
        }
    }
    //数组下标从1开始，往下调整
    private void shiftDown1(int position){
        int leftChild = 2 * position+1;
        int rightChild = 2 * position + 2;
        int j = leftChild;
        //j<=count-1的判断是很有技巧的，也就是j>=count了，则j的父节点肯定就是叶子节点了，则调整结束，跳出循环就ok
        //特别注意数组下标是从0开始的，一定要注意边界问题
        while (j<=count-1) {
            //j指向position最大的一个孩子
            //注意这个地方必须要进行判断，rightChild要小于count才可以，要不然右孩子有可能不存在
            if (rightChild<=count-1 && _compare(j, rightChild) == -1)
                j = rightChild;

            if (_compare(position, j) == -1)
                _swap(position, j);
            else
                break;
            //往下扫描
            position = j;
            //j始终指向leftChild
            j = 2 * position+1;
            rightChild = 2 * position + 2;
        }
    }

    //用于迭代堆中的所有元素
    private class Itr implements Iterator<T> {
        int size = 1;

        public boolean hasNext() {
            return size <= count;
        }

        public T next() {
            return (T) objects[size++];
        }

        public void remove() {
            throw new RuntimeException("not support remove");
        }
    }

    //来调整堆，从下往上的进行调整
    private void shiftUp(int position) {
        //得到其父节点的位置
        int parent = position / 2;
        //一定要将parent>1写在判断前面，可以加快效率，还有一点就是当加入一个元素时，如果先判断比较的话，会出现null异常，因为1/2=0 ,但是0位置为null
        while (parent >= 1 && _compare(position, parent) == 1) {
            _swap(position, parent);
            //当前节点要往上移动，不能漏
            position = parent;
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
