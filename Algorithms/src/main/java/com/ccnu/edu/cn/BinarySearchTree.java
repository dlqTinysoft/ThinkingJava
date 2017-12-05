package com.ccnu.edu.cn;

import java.util.Comparator;

/**
 * Created by 董乐强 on 2017/12/3.
 * 二叉搜索树的实现
 */
public class BinarySearchTree<K,V> {

       private Comparator<? extends K> comparator;
       //二叉搜索树中的元素个数
       private int count;
       //二叉搜素树的根结点
       private Node<K,V> root;
       private class Node<K,V>{
           private K key;
           private V value;
           private Node<K,V> leftChild;
           private Node<K,V> rightChild;
           public Node(K key ,V value){
               this.key=key;
               this.value = value;
           }
       }

       public BinarySearchTree(){
           this.comparator = null;
       }
       //传入一个比较器
       public BinarySearchTree(Comparator<? extends  K> comparator){
           this.comparator = comparator;
       }

       public void insert(K key,V value){
           root = _insert(root,key,value);
       }

       private Node<K,V> _insert(Node<K,V> node ,K key,V value){
              //得到一个新结点
              Node<K,V> newNode = new Node<>(key,value);
              if(node == null)
                  return newNode;

              return newNode;
       }

       private int _compare(Object source ,Object target ){

           return 1;
       }
}
