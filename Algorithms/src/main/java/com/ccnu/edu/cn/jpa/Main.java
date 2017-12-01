package com.ccnu.edu.cn.jpa;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 董乐强 on 2017/11/25.
 */
public class Main {

    public static void main(String [] args){

        String persistence_unit="jpa-1";

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistence_unit);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Customer customer = new Customer();
        customer.setAge(12);
        customer.setEmail("tom@atguigu.com");
        customer.setLastName("Tom");
        customer.setBirth(new Date());
        customer.setCreatedTime(new Date());

        entityManager.persist(customer);

        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();

    }
}
