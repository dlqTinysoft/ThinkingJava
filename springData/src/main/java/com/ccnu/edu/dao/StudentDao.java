package com.ccnu.edu.dao;

import com.ccnu.edu.domain.Student;

import java.util.List;

/**
 * Created by 董乐强 on 2017/11/29.
 * StudentDao访问接口
 */
public interface StudentDao {
    /**
     * 查询所有学生
     * @return 所有学生
     */
     List<Student> query();

    /**
     * 保存学生
     * @param student
     */
     void save(Student student);
}
