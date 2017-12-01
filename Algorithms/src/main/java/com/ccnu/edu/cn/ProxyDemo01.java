package com.ccnu.edu.cn;


import org.junit.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

/**
 * Created by 董乐强 on 2017/11/26.
 */
public class ProxyDemo01 {
    @org.junit.Test
    public void test() {
        SortClass sortClass = new SortClassImpl();
        Student [] students = new Student[10000];
        Random r = new Random();
        sortClass.speedSort(acquireRandomData(students,r));
        sortClass.speedSort1(acquireRandomData(students,r));
        sortClass.speedSort2(acquireRandomData(students,r));
        System.out.println(sortClass);
        SortClass proxySortClass = (SortClass) Proxy.newProxyInstance(sortClass.getClass().getClassLoader(),
                sortClass.getClass().getInterfaces(),
                new InvocationHandler() {

                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println(method.getName());
                        return null;
                    }
                });
        System.out.println(proxySortClass);

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
