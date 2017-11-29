package com.tinysoft.cn;

import org.junit.*;

import javax.persistence.*;

/**
 * Created by 董乐强 on 2017/11/27.
 */
@Table(name = "jpa_department")
@Entity
public class Department {

    @GeneratedValue
    @Id
    private Integer id;
    @Column(name = "DEPT_NAME")
    private String deptName;

    //用来维护关联关系,因为是一对一，所以必须唯一呀
    @JoinColumn(name = "MGR_ID",unique = true)
    //使用懒加载
    @OneToOne(fetch = FetchType.LAZY)
    private Manager mgr;

    public Department() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Manager getMgr() {
        return mgr;
    }

    public void setMgr(Manager mgr) {
        this.mgr = mgr;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", mgr=" + mgr +
                '}';
    }
}
