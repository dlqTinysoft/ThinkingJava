package com.ccnu.edu.cn;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Comparator;

/**
 * Created by 董乐强 on 2017/11/23.
 */
public class Test {



    @org.junit.Test
    public void test(){
        SortClass<Student> sortClass = new SortClass<Student>(new Comparator<Student>() {
            public int compare(Student source, Student target) {
                if(source.getAge()>target.getAge()){
                    return 1;
                }else if(source.getAge()<target.getAge()){
                    return -1;
                }
                return 0;
            }
        });
        Student student1 = new Student(1,"张三",23);
        Student student2 = new Student(1,"李四",13);
        Student student3 = new Student(1,"王五",25);
        Student student4 = new Student(1,"赵六",19);
        Student student5 = new Student(1,"田七",27);
        Student [] students = new Student[]{student1,student2,
                student3,student4,student5};

        sortClass.selectSort(students);

        for(Student student:students){
            System.out.println(student.getName()+"--"+student.getAge());
        }

    }

    @org.junit.Test
    public void test1(){
        Integer [] data = new Integer[]{1,5,9,0,3,2,18};
        SortClass<Integer> sortClass = new SortClass();
        //sortClass.selectSort(data);
        sortClass.insertSortPromotion(data);
        for(Integer i:data){
            System.out.print(i+" ");
        }









    }











}
