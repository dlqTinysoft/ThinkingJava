package com.ccnu.edu.cn;

/**
 * Created by 董乐强 on 2017/11/23.
 */
public class Student implements  Comparable<Student> {
    private Integer id;
    private String name;
    private Integer age;

    public Student(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int compareTo(Student o) {
        return this.getAge()>o.getAge()?1:(this.getAge()==o.getAge()?0:-1);
    }
}
