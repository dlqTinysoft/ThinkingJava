package com.ccnu.edu.service;

import com.ccnu.edu.domain.Employee;
import com.ccnu.edu.respository.EmployeeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 董乐强 on 2017/11/29.
 */
public class EmployeeServiceTest {
    private ApplicationContext ctx=null;
    private EmployeeService employeeService=null;
    @Before
    public void init(){
        ctx = new ClassPathXmlApplicationContext("new_beans.xml");
        employeeService=ctx.getBean(EmployeeService.class);
    }
    @After
    public void destory(){
        ctx=null;
    }

    @Test
    public void testUpdate(){
        employeeService.update(2,55);
    }

    @Test
    public void testSave(){
        List<Employee> employees = new ArrayList<Employee>();
        Employee employee = null;
        for(int i = 1 ; i<100;i++){
            employee = new Employee();
            employee.setName("test"+i);
            employee.setAge(100-i);
            employees.add(employee);
        }
        employeeService.save(employees);
    }


}
