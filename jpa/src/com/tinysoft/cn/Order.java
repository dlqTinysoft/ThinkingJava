package com.tinysoft.cn;

import javax.persistence.*;

/**
 * Created by 董乐强 on 2017/11/27.
 */
@Table(name = "jpa_order")
@Entity
public class Order {
    @GeneratedValue
    @Id
    private Integer id;
    @Column(name = "ORDER_NAME")
    private String orderName;
    //维持多对一的关系，在开发运用的最广泛
    //JoinColumn 后面name为外键
    @JoinColumn(name = "CUSTOMER_ID")
    //懒加载后，不会用左外连接，不使用，就不会发送sql
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    public Order() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }


}
