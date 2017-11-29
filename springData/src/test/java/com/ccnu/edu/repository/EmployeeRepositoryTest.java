package com.ccnu.edu.repository;

import com.ccnu.edu.domain.Employee;
import com.ccnu.edu.respository.EmployeeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 董乐强 on 2017/11/29.
 */
public class EmployeeRepositoryTest {
    private ApplicationContext ctx=null;
    private EmployeeRepository employeeRepository=null;
    @Before
    public void init(){
        ctx = new ClassPathXmlApplicationContext("new_beans.xml");
        employeeRepository=ctx.getBean(EmployeeRepository.class);
    }
    @After
    public void destory(){
        ctx=null;
    }
    @Test
    public void testFindByName(){
        System.out.println(employeeRepository);
        Employee employee = employeeRepository.findByName("dlq");
        System.out.println(employee);
    }
    @Test
    public void testFindByNameStartingWithAndLessThan(){
        List<Employee> datas = employeeRepository.findByNameStartingWithAndAgeLessThan("test",18);
        for(Employee employee:datas)
            System.out.println(employee);
    }
    @Test
    public void testfindByNameEndingWithAndAgeLessThan(){
        List<Employee> datas = employeeRepository.findByNameEndingWithAndAgeLessThan("6",25);
        for(Employee employee:datas)
            System.out.println(employee);
    }
    @Test
    public void testfindByNameInOrAgeLessThan(){
        List<String> names = new ArrayList<String>();
        names.add("test6");
        names.add("test3");
        names.add("test5");
        List<Employee> datas = employeeRepository.findByNameInOrAgeLessThan(names,20);
        for(Employee employee:datas)
            System.out.println(employee);
    }
    @Test
    public void testfindByNameInAndAgeLessThan(){
        List<String> names = new ArrayList<String>();
        names.add("test6");
        names.add("test3");
        names.add("test5");
        List<Employee> datas = employeeRepository.findByNameInAndAgeLessThan(names,20);
        for(Employee employee:datas)
            System.out.println(employee);
    }
    @Test
    public void testfindEmployeeByMax(){
        System.out.println(employeeRepository.getEmployeeById());
    }
    @Test
    public void testqueryParams(){
       List<Employee> employees = employeeRepository.queryParams("test6",22);
       for(Employee employee:employees)
           System.out.println(employee);
    }
    @Test
    public void testqueryParams1(){
        List<Employee> employees = employeeRepository.queryParams1(22,"test6");
        for(Employee employee:employees)
            System.out.println(employee);
    }
    @Test
    public void testqueryLike1(){
        List<Employee> employees = employeeRepository.queryLike1("test");
        for(Employee employee:employees)
            System.out.println(employee);
    }

    @Test
    public void testqueryLike2(){
        List<Employee> employees = employeeRepository.queryLike2("test1");
        for(Employee employee:employees)
            System.out.println(employee);
    }
    @Test
    public void testgetCount(){
        System.out.println(employeeRepository.getCount());
    }
    @Test
    public void testUpdate(){
        employeeRepository.upate(1,52);
    }
}
