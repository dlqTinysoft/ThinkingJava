package com.ccnu.edu.domain;

import javax.persistence.*;

/**
 * Created by 董乐强 on 2017/11/29.
 * 先开发实体类，让其生成数据表
 */
@Table(name="EMPLOYEE_TEST")
@Entity
public class Employee {

    @GeneratedValue
    @Id
    private Integer id;
    @Column(length = 20)
    private String name;
    private Integer age;

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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
