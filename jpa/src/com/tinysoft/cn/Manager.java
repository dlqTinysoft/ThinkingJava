package com.tinysoft.cn;

import javax.persistence.*;

/**
 * Created by 董乐强 on 2017/11/27.
 */
@Table(name = "jpa_manager")
@Entity
public class Manager {

    @GeneratedValue
    @Id
    private Integer id;
    @Column(name = "MGR_NAME")
    private String mgrName;

    //这一方不维持关联关系，这样可以减少sql,达到优化的目的
    @OneToOne(mappedBy = "mgr")
    private Department dept;

    public Manager() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMgrName() {
        return mgrName;
    }

    public void setMgrName(String mgrName) {
        this.mgrName = mgrName;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }
}
