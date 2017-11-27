package com.tinysoft.cn;

import org.aspectj.lang.annotation.Before;
import org.aspectj.weaver.ast.Or;
import org.hibernate.dialect.CUBRIDDialect;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 董乐强 on 2017/11/26.
 */
public class Test {

    //jpa的使用步骤
    @org.junit.Test
    public void test(){
        //得到一个EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-1");
        //得到一个entityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //得到事务
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Customer customer = new Customer();
        customer.setAge(18);
        customer.setEmail("dlq@163.com");
        customer.setLastName("dlq");
        customer.setBirth(new Date());
        customer.setCreatedTime(new Date());
        //保存数据
        entityManager.persist(customer);
        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

     private EntityManagerFactory entityManagerFactory;
     private EntityManager entityManager;
     private EntityTransaction entityTransaction;

    @org.junit.Before
    public void init(){
     entityManagerFactory = Persistence.createEntityManagerFactory("jpa-1");
     entityManager = entityManagerFactory.createEntityManager();
     entityTransaction = entityManager.getTransaction();
     entityTransaction.begin();
    }

    @org.junit.After
    public void after(){
     entityTransaction.commit();
     entityManager.close();
     entityManagerFactory.close();
    }

   //查询一条记录
    @org.junit.Test
    //相当于hibernate的get方法，没有设置缓存
    public void testFindone(){
        Customer customer = entityManager.find(Customer.class,1);
        System.out.println(customer);
    }

    @org.junit.Test
    //相当于hibernate的load方法，具有缓存的功能
    public void testGetReference(){
        Customer customer = entityManager.getReference(Customer.class,1);
        System.out.println(customer);

    }
    //persist相当于hibernate的save方法,使对象由临时状态变为持久状态
    //但是如果对象设置了id则插入不进数据库了
    @org.junit.Test
    public void testPersistence(){
        Customer customer = new Customer();
        customer.setAge(20);
        customer.setEmail("dlq@163.com");
        customer.setLastName("dlq");
        customer.setBirth(new Date());
        customer.setCreatedTime(new Date());
        //entityManager.persist(customer);
        Customer customer1 = new Customer();
        customer1.setAge(20);
        customer1.setEmail("dlq@163.com");
        customer1.setLastName("dlq");
        customer1.setBirth(new Date());
        customer1.setCreatedTime(new Date());
        //手动customer1设置id
        //customer1.setId(25);
        entityManager.persist(customer1);
    }

    //类似于hibernate的delete，但是hibernate中delete可以移除游离对象，但是remove不可以
    @org.junit.Test
    public void testRemove(){
        //Customer customer = entityManager.find(Customer.class,1);
        Customer customer = new Customer();
        //不能移游离状态的对象
        customer.setId(2);
        entityManager.remove(customer);
    }

    //entityManager中merge方法的使用 类似于hibernate saveOrUpdate方法
    //若传入的是临时对象，则会创建一个新的对象，将临时对象复制到新对象中，然后对新对象进行持久化操作，
    // 临时对象不做任何处理，新对象有id,临时对象无id
    @org.junit.Test
    public void testMerge(){
        Customer customer = new Customer();
        customer.setCreatedTime(new Date());
        customer.setLastName("yy");
        customer.setEmail("yy@163.com");
        customer.setAge(18);
        customer.setBirth(new Date());
        Customer customer1 = entityManager.merge(customer);
        System.out.println("customer#id: "+customer.getId());
        System.out.println("customer1#id: "+customer1.getId());

    }

    //传入是一个游离对象的情况
    //如果数据库中没有对应的id，且EntityManager中没有缓存，则会创建一个新的对象，将原来的对象的值，
    //复制新的对象中去，对新对象进行持久化操作即插入操作
    @org.junit.Test
    public void testMerge1(){
        Customer customer = new Customer();
        customer.setCreatedTime(new Date());
        customer.setLastName("cc");
        customer.setEmail("cc@163.com");
        customer.setAge(18);
        customer.setId(13);
        customer.setBirth(new Date());
        Customer customer1 = entityManager.merge(customer);
        System.out.println("customer#id: "+customer.hashCode());
        System.out.println("customer1#id: "+customer1.hashCode());

    }

    @org.junit.Test
    //若merge的对象为一个游离状态的对象，但是entityManager没有缓存，但是数据中有对应的id,
    //则从数据库中将查出一个新对象，将源对象的值复制到新对象中，进行更新操作
    public void testMerge2(){
        Customer customer = new Customer();
        customer.setCreatedTime(new Date());
        customer.setLastName("dd");
        customer.setEmail("dd@163.com");
        customer.setAge(18);
        customer.setId(13);
        customer.setBirth(new Date());
        Customer customer1 = entityManager.merge(customer);
        System.out.println("customer#id: "+customer.hashCode());
        System.out.println("customer1#id: "+customer1.hashCode());

    }
    //如果传入一个游离状态的对象，但是entity有缓存，则jpa将游离对象的值复制到entityManager的缓存对象中去，
    //缓存对象执行update操作
    @org.junit.Test
    public void testMerge3(){
        Customer customer = new Customer();
        customer.setAge(18);
        customer.setBirth(new Date());
        customer.setCreatedTime(new Date());
        customer.setEmail("dd@163.com");
        customer.setLastName("DD");

        customer.setId(4);
      Customer customer1 = entityManager.find(Customer.class,4);
      entityManager.merge(customer);
      System.out.println(customer==customer1);
    }

    //entityManager中的flush方法，相当于hibernate中flush方法
    @org.junit.Test
    public void testFlush(){
        Customer customer = entityManager.find(Customer.class,4);
        customer.setAge(25);
        customer.setEmail("drddd@163.com");
        entityManager.flush();
    }

    //jpa中的refresh方法没有搞懂，什么情况??? refresh就是缓存有该对象，但想将其在此刷出缓存
    @org.junit.Test
    public void testReflush(){
        Customer customer = entityManager.find(Customer.class,2);
        customer = entityManager.find(Customer.class,2);
        entityManager.refresh(customer);
    }

    //多对一的设置，多的一方维持关联，这样有很大的好处
    //先保存一的一方，然后在保存多的一方，这样可以不用执行update了，如果先保存多的一方，还要多出update操作
    @org.junit.Test
    public void testManyToOne(){
        Customer customer = new Customer();
        customer.setAge(18);
        customer.setBirth(new Date());
        customer.setCreatedTime(new Date());
        customer.setEmail("gg@163.com");
        customer.setLastName("GG");

        Order order1 = new Order();
        order1.setOrderName("G-GG-1");

        Order order2 = new Order();
        order2.setOrderName("G-GG-2");

        //order1.setCustomer(customer);
        //order2.setCustomer(customer);
        entityManager.persist(customer);
        entityManager.persist(order1);
        entityManager.persist(order2);

    }

    //Manytoone中的fetch用来设置关联属性的加载策略，使用FetchType.lazy来实现懒加载策略
    //不使用关联属性，就不会发sql,这也是一种优化方式
    @org.junit.Test
    public void testManytoOneFind(){
        Order order = entityManager.find(Order.class,5);
        System.out.println(order);
        //System.out.println(order.getCustomer());
    }
    //ManyToOne删除操作，可以删除多的一方，但是不能删除一的一方，删除多的一方，不会删除一的一方
    @org.junit.Test
    public void testManytoOneRemove(){
        //Order order = entityManager.find(Order.class,6);
        //删除多的一方，可以进行正常删除
        //entityManager.remove(order);

        //删除一的一方,不能够删除，因为有外键约束呀，所以无法删除的
        Customer customer = entityManager.find(Customer.class,27);
        entityManager.remove(customer);
    }

    @org.junit.Test
    //manytoone更新操作,在这里更新操作可以不用懒加载，用懒加载则会发送三条sql语句，不用懒加载则发送两条sql语句
    //如果没有懒加载，用的是左外连接的方法
    public void testManyToOneUpdate(){
        Order  order = entityManager.find(Order.class,10);
        //order.getCustomer().setEmail("sfsdsdfsdffd@163.com");
        //order.getCustomer().setLastName("hhdsdfsdfdds");
    }

   //单向1对n的关联操作
    //注意，在这里会执行3条insert语句，2条update,因为多的一方没有维持关联关系，所以多的一方
    //在插入的时候，不会插入外键的值
    @org.junit.Test
    public void testOneToManySave(){
        Customer customer = new Customer();
        customer.setAge(18);
        customer.setBirth(new Date());
        customer.setCreatedTime(new Date());
        customer.setEmail("mm@163.com");
        customer.setLastName("MM");

        Order order1 = new Order();
        order1.setOrderName("O-MM-1");

        Order order2 = new Order();
        order2.setOrderName("O-MM-2");

        //建立关联关系
        customer.getOrders().add(order1);
        customer.getOrders().add(order2);

        entityManager.persist(customer);
        entityManager.persist(order1);
        entityManager.persist(order2);

    }

    //单向一对多的查询操作
    //查询操作时，一的一端默认使用懒加载机制,可以在onetomany这个注解上进行修改，从新设置加载机制fecth=FetchType.lazy
    //不用懒加载机制后，使用的是左外连接将多的一端数据查出来
    @org.junit.Test
    public void testOneToManyFind(){
        Customer customer = entityManager.find(Customer.class,7);
        //System.out.println(customer.getOrders().iterator().next().getOrderName());
    }

    //单向一对多删除操作
    //默认情况下，删除一的一方，会把多的一方的外键置空，这样做事非常不好的，多的一方将成为僵尸对象了
    //可以在oneTomany上进行修改，加上级联删除的操作
    @org.junit.Test
    public void testOneToManyDelete(){
        Customer customer = entityManager.find(Customer.class,3);
        entityManager.remove(customer);
    }

    //单向一对多更新操作
    @org.junit.Test
    public void testOneToManyUpdate(){
           Customer customer = entityManager.find(Customer.class,1);
           customer.getOrders().iterator().next().setOrderName("DLQDLQ");
    }

    //双向一对多关联关系，保存操作
    //双向的操作的性能非常不好，这样我们可以优化以下，上多的一方来维持关联关系，这样会很好的
    //在onetomany上使用mappedBy="customer" 是一的一方放弃维护就ok了但是一的一方不能用joinColunm这个注解了
    @org.junit.Test
    public void testOneToMany2Save(){
        Customer customer = new Customer();
        customer.setAge(25);
        customer.setLastName("dlq");
        customer.setEmail("dlq@163.com");
        customer.setCreatedTime(new Date());
        customer.setBirth(new Date());


        Order order1 = new Order();
        order1.setOrderName("YY");

        Order order2 = new Order();
        order2.setOrderName("GG");


        customer.getOrders().add(order1);
        customer.getOrders().add(order2);

        order1.setCustomer(customer);
        order2.setCustomer(customer);


        entityManager.persist(customer);
        entityManager.persist(order1);
        entityManager.persist(order2);

    }













    //双向1对1关联关系，保存操作
    @org.junit.Test
    public void testOneToOnePersistence(){

    }










}





