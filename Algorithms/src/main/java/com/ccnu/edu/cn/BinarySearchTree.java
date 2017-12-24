package com.ccnu.edu.cn;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Queue;

/**
 * Created by 董乐强 on 2017/12/3.
 * 二叉搜索树的实现
 * 为了使用compare这个方法，所以要继承SortClassImpl这个类，因为这个类中存在_compare(),重构代码的时候，可以_compare()作为一个公具类来操作
 */
public class BinarySearchTree<K, V> extends SortClassImpl<K> {

    private Comparator<? extends K> comparator;
    //二叉搜索树中的元素个数
    private int count;
    //二叉搜素树的根结点
    private Node<K, V> root;
    //用于存储遍历的元素
    //能不能将count直接赋值到数组中，作为数组的大小
    private Node<K, V>[] datas;
    private Object[] data;
    private int index;

    //内部类，定义二叉树的一个节点
    private class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> leftChild;
        private Node<K, V> rightChild;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public BinarySearchTree() {
        this.comparator = null;
    }

    //传入一个比较器
    public BinarySearchTree(Comparator<? extends K> comparator) {
        this.comparator = comparator;
    }

    public int getCount(){
        return count;
    }

    public Object[] getKeys() {
        data = new Object[count];
        if(datas.length>0) {
            for (int i = 0; i < count; i++) {
                data[i] = datas[i].key;
            }
        }
        return data;
    }

    public Object[] getValues() {
        data = new Object[count];
        if(datas.length>0) {
            for (int i = 0; i < count; i++) {
                data[i] = datas[i].value;
            }
        }
        return data;
    }


    /**
     * 递归实现将元素插入到二叉排序树中
     *
     * @param key
     * @param value
     */
    public void insert(K key, V value) {
        root = _insert(root, key, value);
    }

    public void insert1(K key,V value){
        root = _insert1(root,key,value);
    }
    /**
     * 二叉树是否包含key值
     *
     * @param key
     * @return
     */
    public boolean containKeys(K key) {
        boolean flag = _containKeys(root, key);
        return flag;
    }

    /**
     * 通过得到value
     *
     * @param key
     * @return
     */
    public V getValue(K key) {
        if (!_containKeys(root, key))
            throw new RuntimeException("Binary search tree don't have key");
        return _getValue(root, key);
    }

    /**
     * 二叉树的前序遍历,遍历的数据存入一个数组中
     */
    public void tranverse(int i) {
        datas = new Node[count];
        index = 0;
        if (i == 0)
            _preOrder(root);
        else if (i == 1)
            _middleOrder(root);
        else if (i == 2)
            _afterOrder(root);
    }

    private void _middleOrder(Node<K, V> node) {
        if (node == null)
            return;
        _middleOrder(node.leftChild);
        datas[index++] = node;
        _middleOrder(node.rightChild);

    }

    private void _preOrder(Node<K, V> node) {
        if (node == null)
            return;
        datas[index++] = node;
        _preOrder(node.leftChild);
        _preOrder(node.rightChild);
    }

    private void _afterOrder(Node<K, V> node) {
        if (node == null)
            return;
        _afterOrder(node.leftChild);
        _afterOrder(node.rightChild);
        datas[index++] = node;
    }

    /**
     * 层次遍历，需要一个辅助队列
     */
    public void transLevel(){
        //得到一个辅助队列，来进行层序遍历
        ArrayDeque<Node<K,V>> queue = new ArrayDeque();
        //不能带着泛型，来创建数组
        datas= new Node[count];
        //根结点入队
        queue.addLast(root);
        while(!queue.isEmpty()){
            Node<K,V> head = queue.getFirst();
            datas[index++] =queue.pop();
            if(head.leftChild!=null)
                queue.addLast(head.leftChild);
            if(head.rightChild!=null)
                queue.addLast(head.rightChild);
        }
    }

    /**
     * 以node为根结点中，查找key对应的value
     *
     * @param node
     * @param key
     * @return
     */
    private V _getValue(Node<K, V> node, K key) {
        if (_compare(node.key, key) == 0)
            return node.value;
        //以左孩子为节点进行寻找
        if (_compare(node.key, key) == 1)
            return _getValue(node.leftChild, key);
        else
            return _getValue(node.rightChild, key);
    }


    /**
     * 以node为节点中，是否包含键值key
     *
     * @param node
     * @param key
     * @return
     */
    private boolean _containKeys(Node<K, V> node, K key) {
        if (node == null)
            return false;
        //以左孩子为根结点进行查找
        if (_compare(node.key, key) == 1)
            return _containKeys(node.leftChild, key);

            //以右孩子为根结点进行查找
        else if (_compare(node.key, key) == -1)
            return _containKeys(node.rightChild, key);
        else
            return true;
    }

    /**
     * 二分排序树的插入，非递归实现,还没有实现完成
     *
     * @param key
     * @param value
     */
    private Node<K, V> _insert1(Node<K, V> root, K key, V value) {
        //得到一个新节点
        Node<K, V> newNode = new Node<>(key, value);
        //当前指向的节点
        Node<K, V> currentNode = root;
        //父节点，必须要有父节点，因为currentNode走到null时，其父节点决定当前节点的插入位置
        Node<K, V> parentNode = root;
        if (root == null) {
            count++;
            root = newNode;
        }
        else {
            //寻找要插入的节点，如果currentNode == null，则找到要插入的节点
            while (currentNode != null) {
                parentNode = currentNode;
                if (_compare(currentNode.key, key) == 1)
                    currentNode = currentNode.leftChild;
                else if (_compare(currentNode.key, key) == -1)
                    currentNode = currentNode.rightChild;
                else {
                    currentNode.key = key;
                    break;
                }
            }
            if (currentNode == null) {
                count++;
                if (_compare(parentNode.key, key) == 1)
                    parentNode.leftChild = newNode;
                else
                    parentNode.rightChild = newNode;
            }
        }
        //返回根结点
        return root;
    }


    /**
     * 二分查找树，插入一个节点
     * 递归实现
     * 以node为节点的二叉树中插入节点
     *
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node<K, V> _insert(Node<K, V> node, K key, V value) {

        //得到一个新结点
        Node<K, V> newNode = new Node<>(key, value);

        if (node == null) {
            count++; //这个是递归的结束条件，程序一定会走到这一步如果
            return newNode;
        }
        if (_compare(node.key, key) == -1)
            node.rightChild = _insert(node.rightChild, key, value);
        else if (_compare(node.key, key) == 1)
            node.leftChild = _insert(node.leftChild, key, value);
        else {
            node.key = key;
            return node;
        }

        return node;
    }

}
