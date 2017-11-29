package com.ccnu.edu.repository;

import com.ccnu.edu.dao.EmployeeJpaReoposity;
import com.ccnu.edu.domain.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 董乐强 on 2017/11/29.
 */
public class EmployeeJpaRepositoryTest {
    private ApplicationContext ctx=null;
    private EmployeeJpaReoposity employeeJpaReoposity=null;
    @Before
    public void init(){
        ctx = new ClassPathXmlApplicationContext("new_beans.xml");
        employeeJpaReoposity=ctx.getBean(EmployeeJpaReoposity.class);
    }
    @After
    public void destory(){
        ctx=null;
    }

    @Test
    public void testFind(){
        Employee employee = employeeJpaReoposity.findOne(99);
        System.out.println(employee);
    }
}
