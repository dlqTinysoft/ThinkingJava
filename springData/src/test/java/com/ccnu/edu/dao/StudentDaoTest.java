package com.ccnu.edu.dao;

import com.ccnu.edu.domain.Student;
import org.junit.Test;

import javax.print.DocFlavor;
import java.util.List;

/**
 * Created by 董乐强 on 2017/11/29.
 */
public class StudentDaoTest {

    @Test
    public void testQuery(){
        StudentDao studentDao = new StudentDaoImpl();
        List<Student> students =  studentDao.query();
        for(Student student:students){
            System.out.println(student);
        }
    }
    @Test
    public void testSave(){
        StudentDao studentDao = new StudentDaoImpl();
        Student student = new Student();
        student.setId(6);
        student.setName("dlq");
        student.setAge("25");
        studentDao.save(student);
    }
}
