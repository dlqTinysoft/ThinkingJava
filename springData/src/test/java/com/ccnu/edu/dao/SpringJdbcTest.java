package com.ccnu.edu.dao;

import com.ccnu.edu.domain.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by 董乐强 on 2017/11/29.
 */
public class SpringJdbcTest {
    private ApplicationContext ctx = null;
    @Test
    public void testQuery(){
        StudentDao studentDao = (StudentDao) ctx.getBean("studentDao");
       List<Student> studentList = studentDao.query();
       for(Student student :studentList)
           System.out.println(student);
    }

    @Test
    public void testSave(){
        StudentDao studentDao = (StudentDao) ctx.getBean("studentDao");
        Student student = new Student();
        student.setId(7);
        student.setAge("255");
        student.setName("WSJAJ");
        studentDao.save(student);
    }
    @Before
    public void init(){
        ctx = new ClassPathXmlApplicationContext("beans.xml");
    }
    @After
    public void destory(){
        ctx=null;
    }


}
