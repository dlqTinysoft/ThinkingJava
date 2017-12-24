package com.ccnu.edu.cn;

import org.junit.Test;
/**
 * Created by 董乐强 on 2017/12/7.
 */
public class BinarySearchTreeTest {

    private BinarySearchTree<Integer,Student> binarySearchTree = new BinarySearchTree<>();
    @Test
    public void insert1() throws Exception {
        binarySearchTree.insert1(2,new Student(1,"张三",25));
        binarySearchTree.insert1(4,new Student(2,"李四",37));
        binarySearchTree.insert1(5,new Student(3,"王五",22));
        binarySearchTree.insert1(1,new Student(4,"赵六",17));
        binarySearchTree.insert1(3,new Student(3,"田七",25));
        binarySearchTree.insert1(7,new Student(4,"dd",17));
        binarySearchTree.tranverse(1);
        Object [] keys = binarySearchTree.getKeys();
        for(Object o :keys)
            System.out.print(o+" ");
    }

    @Test
    public void containKeys() throws Exception {
        binarySearchTree.insert1(2,new Student(1,"张三",25));
        binarySearchTree.insert1(4,new Student(2,"李四",37));
        binarySearchTree.insert1(5,new Student(3,"王五",22));
        binarySearchTree.insert1(1,new Student(4,"赵六",17));
        binarySearchTree.insert1(3,new Student(3,"田七",25));
        binarySearchTree.insert1(7,new Student(4,"dd",17));
        System.out.println(binarySearchTree.containKeys(8));
    }

    @Test
    public void getValue() throws Exception {
        binarySearchTree.insert1(2,new Student(1,"张三",25));
        binarySearchTree.insert1(4,new Student(2,"李四",37));
        binarySearchTree.insert1(5,new Student(3,"王五",22));
        binarySearchTree.insert1(1,new Student(4,"赵六",17));
        binarySearchTree.insert1(3,new Student(3,"田七",25));
        binarySearchTree.insert1(7,new Student(4,"dd",17));

        String name = binarySearchTree.getValue(7).getName();
        System.out.println(name);

    }

    @Test
    public void tranverse() throws Exception {
        binarySearchTree.insert1(2,new Student(1,"张三",25));
        binarySearchTree.insert1(4,new Student(2,"李四",37));
        binarySearchTree.insert1(5,new Student(3,"王五",22));
        binarySearchTree.insert1(1,new Student(4,"赵六",17));
        binarySearchTree.insert1(3,new Student(3,"田七",25));
        binarySearchTree.insert1(7,new Student(4,"dd",17));
        binarySearchTree.transLevel();
        Object [] keys = binarySearchTree.getKeys();
        for(Object o :keys)
            System.out.print(o+" ");
    }

    @org.junit.Test
    public void insert() throws Exception {

        BinarySearchTree<Integer,Student> binarySearchTree = new BinarySearchTree<>();
        Student student = new Student(1,"DLQ",12);
        binarySearchTree.insert(1,student);
    }

}