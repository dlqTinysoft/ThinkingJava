package com.ccnu.edu.respository;


import com.ccnu.edu.domain.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by 董乐强 on 2017/11/29.
 */
@RepositoryDefinition(domainClass = Employee.class,idClass = Integer.class)
public interface EmployeeRepository extends Repository<Employee,Integer>{
     public Employee findByName(String name);
     //select * from employee where name like"name%" and age<?
     public List<Employee> findByNameStartingWithAndAgeLessThan(String name,Integer age);
     //select * from employee where name like "%name" and age<?
     public List<Employee> findByNameEndingWithAndAgeLessThan(String name,Integer age);
     //where name in() or age<?
     public List<Employee> findByNameInOrAgeLessThan(List<String> names,Integer age);
     //where name in() and age<?
     public List<Employee> findByNameInAndAgeLessThan(List<String> names,Integer age);

     @Query("select o from Employee o where id=(select max(id) from Employee t1)")
     public Employee getEmployeeById();

     @Query("select o from Employee o where o.name=?1 and o.age=?2")
     public List<Employee> queryParams(String name, Integer age );

     @Query("select o from Employee o where o.name=:name and o.age=:age")
     public List<Employee> queryParams1(@Param("age") Integer age,@Param("name") String name);

     @Query("select o from Employee o where o.name like %?1%")
     public List<Employee> queryLike1(String name);
     @Query("select o from Employee o where o.name like %:name%")
     //参数绑定
     public List<Employee> queryLike2(@Param("name") String name);
     //使用原生的查询sql来查询
     @Query(nativeQuery = true,value = "select count(*) from employee")
     public long getCount();
     //DML必须使用@Modifying这个注解
     @Modifying
     @Query("update Employee o set o.age=:age where o.id=:id")
     public void upate(@Param("id") Integer id,@Param("age") Integer age);

}
