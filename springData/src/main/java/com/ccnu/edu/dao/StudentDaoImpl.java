package com.ccnu.edu.dao;

import com.ccnu.edu.domain.Student;
import com.ccnu.edu.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 董乐强 on 2017/11/29.
 */
public class StudentDaoImpl implements  StudentDao {
    public List<Student> query() {
        List<Student> students = new ArrayList<Student>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql="select * from student ;";
        try {
           connection= JDBCUtil.getConnection();
           preparedStatement = connection.prepareStatement(sql);
           resultSet = preparedStatement.executeQuery();

           while(resultSet.next()){
               int id = resultSet.getInt("id");
               String name = resultSet.getString("name");
               String age = resultSet.getString("age");
               Student student = new Student();
               student.setAge(age);
               student.setId(id);
               student.setName(name);
               students.add(student);
           }
        } catch (Exception e) {

            e.printStackTrace();
        }finally {
            JDBCUtil.release(resultSet,preparedStatement,connection);
        }
        return students;
    }

    public void save(Student student) {
        String sql="insert into student (id,name,age) values(?,?,?);";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection= JDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,student.getId());
            preparedStatement.setObject(2,student.getName());
            preparedStatement.setObject(3,student.getAge());
            preparedStatement.executeUpdate();
        } catch (Exception e) {

            e.printStackTrace();
        }finally {
            JDBCUtil.release(resultSet,preparedStatement,connection);
        }
    }
}
