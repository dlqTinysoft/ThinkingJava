package com.ccnu.edu.cn;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import org.hibernate.annotations.Sort;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by 董乐强 on 2017/11/23.
 */
public class Test {
    @org.junit.Test
    public  void application(){
       SortClassImpl<Student> studentSortClass = new SortClassImpl<>();
        /**
         * 第一个参数：被代理类的父类型
         * 第二个参数:InvocationHandler接口
         */
        SortClassImpl<Student> sortClass = (SortClassImpl<Student>) Enhancer.
                create(SortClassImpl.class, new InvocationHandler() {
            /**
             * @param o 代理对象
             * @param method 当前调用代理的哪个方法
             * @param args 当前方法的参数
             * @return 方法返回值
             * @throws Throwable
             */
            @Override
            public Object invoke(Object o, Method method, Object[] args) throws Throwable {
                return method.invoke(studentSortClass,args);
            }
        });

        Random r = new Random();
        Student [] students = new Student[10000];
        Student student = null;
        students=acquireRandomData(students,r);
        sortClass.speedSort(students);

        students=acquireRandomData(students,r);
        sortClass.speedSort1(students);

        students=acquireRandomData(students,r);
        sortClass.speedSort2(students);

        students=acquireRandomData(students,r);
        sortClass.insertSort(students);

        students=acquireRandomData(students,r);
        sortClass.insertSortPromotion(students);

        students=acquireRandomData(students,r);
        sortClass.selectSort(students);


        students=acquireRandomData(students,r);
        sortClass.mergeSort(students);

        students=acquireRandomData(students,r);
        sortClass.mergeSortPromotion(students);
    }

    private static Student [] acquireRandomData(Student [] students,Random r){
        Student student = null;
        //初始化100万个学生信息
        for(int i=0;i<10000;i++){
            student = new Student(i+1,"张"+i+1,r.nextInt(100));
            students[i]=student;
        }
        return students;
    }





















}
