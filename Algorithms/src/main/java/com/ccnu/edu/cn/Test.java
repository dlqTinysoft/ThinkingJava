package com.ccnu.edu.cn;

import java.util.Comparator;
import java.util.Random;

/**
 * Created by 董乐强 on 2017/11/23.
 */
public class Test {

    @org.junit.Test
    public static void main(String [] args){

        SortClassImpl sortClass = new SortClassImpl(new Comparator<Student>() {
            public int compare(Student source, Student target) {
                if(source.getAge()>target.getAge()){
                    return 1;
                }else if(source.getAge()<target.getAge()){
                    return -1;
                }
                return 0;
            }
        });

        Random r = new Random();
        Student [] students = new Student[10000];
        Student student = null;
        students=acquireRandomData(students,r);
        long begin = System.currentTimeMillis();
        sortClass.speedSort(students);
        long end = System.currentTimeMillis();
        System.out.println("单路快速排序: "+"\t\t\t"+(end-begin));

        students=acquireRandomData(students,r);
        begin = System.currentTimeMillis();
        sortClass.speedSort1(students);
        end = System.currentTimeMillis();
        System.out.println("二路快速排序: "+"\t\t\t"+(end-begin));

        students=acquireRandomData(students,r);
        begin = System.currentTimeMillis();
        sortClass.speedSort2(students);
        end = System.currentTimeMillis();
        System.out.println("三路快速排序: "+"\t\t\t"+(end-begin));

        students=acquireRandomData(students,r);
        begin = System.currentTimeMillis();
        sortClass.insertSort(students);
        end = System.currentTimeMillis();
        System.out.println("插入排序(交换): "+"\t\t"+(end-begin));

        students=acquireRandomData(students,r);
        begin = System.currentTimeMillis();
        sortClass.insertSortPromotion(students);
        end = System.currentTimeMillis();
        System.out.println("插入排序(移动): "+"\t\t"+(end-begin));

        students=acquireRandomData(students,r);
        begin = System.currentTimeMillis();
        sortClass.selectSort(students);
        end = System.currentTimeMillis();
        System.out.println("选择排序: "+"\t\t\t"+(end-begin));

        students=acquireRandomData(students,r);
        begin = System.currentTimeMillis();
        sortClass.mergeSort(students);
        end = System.currentTimeMillis();
        System.out.println("归并排序(递归): "+"\t\t"+(end-begin));

        students=acquireRandomData(students,r);
        begin = System.currentTimeMillis();
        sortClass.mergeSortPromotion(students);
        end = System.currentTimeMillis();
        System.out.println("归并排序(非递归): "+"\t\t"+(end-begin));
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
