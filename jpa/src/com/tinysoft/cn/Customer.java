package com.tinysoft.cn;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 董乐强 on 2017/11/26.
 */
@Table(name = "JPA_CUSTOMER")
@Entity
public class Customer {

    @GeneratedValue(strategy =GenerationType.IDENTITY )
    @Id
    private Integer id;
    @Column(name = "LAST_NAME",length = 20,nullable = false)
    private String lastName;

    private String email;
    private int age;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
    @Temporal(TemporalType.DATE)
    private Date birth;
    //单向1对n映射关系
    //@JoinColumn(name = "CUSTOMER_ID")
    //配置加载方式，默认使用的是懒加载机制,即FetchType.lazy
    //使用mappedBy放弃一的一方维护关联关系，这样可以达到优化的效果,但是使用mappedBy这个属性，JoinColumn不能在使用
    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE},mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();
    public Customer() {
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", createdTime=" + createdTime +
                ", birth=" + birth +
                '}';
    }
}
