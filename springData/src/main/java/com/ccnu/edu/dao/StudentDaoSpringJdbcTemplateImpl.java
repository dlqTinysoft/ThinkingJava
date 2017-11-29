package com.ccnu.edu.dao;

import com.ccnu.edu.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 董乐强 on 2017/11/29.
 * 通过Springjdbc的方式访问
 */
public class StudentDaoSpringJdbcTemplateImpl implements StudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Student> query() {
        final List<Student> students = new ArrayList<Student>();
        String sql="select * from student ;";
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String age = resultSet.getString("age");
                Student student = new Student();
                student.setAge(age);
                student.setId(id);
                student.setName(name);
                students.add(student);
            }
        });
        return students;
    }

    public void save(Student student) {
        String sql="insert into student (id,name,age) values(?,?,?);";
        jdbcTemplate.update(sql,new Object[]{student.getId(),student.getName(),student.getAge()});
    }
}
