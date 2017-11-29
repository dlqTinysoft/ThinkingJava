package com.ccnu.edu.repository;

import com.ccnu.edu.dao.EmployeePagingAndSortingRepository;
import com.ccnu.edu.domain.Employee;
import com.ccnu.edu.respository.EmployeeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Created by 董乐强 on 2017/11/29.
 */
public class EmployeePagingAndSortingRepositoryTest {
    private ApplicationContext ctx=null;
    private EmployeePagingAndSortingRepository employeePagingAndSortingRepository=null;
    @Before
    public void init(){
        ctx = new ClassPathXmlApplicationContext("new_beans.xml");
        employeePagingAndSortingRepository=ctx.getBean(EmployeePagingAndSortingRepository.class);
    }
    @After
    public void destory(){
        ctx=null;
    }

    //分页，排序
    @Test
    public void testPage(){
        //page的index是从0开始的
        Pageable pageable = new PageRequest(1,9);
        Page<Employee> page = employeePagingAndSortingRepository.findAll(pageable);

        System.out.println("总页数: "+page.getTotalPages());

        System.out.println("总记录数: "+page.getTotalElements());

        System.out.println("当前第几页: "+(page.getNumber()+1));

        System.out.println("当前页面的集合: "+page.getContent());

        System.out.println("查询当前的记录数: "+page.getNumberOfElements());
    }

    @Test
    public void testPageAndSort(){
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"id");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(1,9,sort);
        Page<Employee> page = employeePagingAndSortingRepository.findAll(pageable);

        System.out.println("总页数: "+page.getTotalPages());

        System.out.println("总记录数: "+page.getTotalElements());

        System.out.println("当前第几页: "+(page.getNumber()+1));

        System.out.println("当前页面的集合: "+page.getContent());

        System.out.println("查询当前的记录数: "+page.getNumberOfElements());
    }



}
