package com.ccnu.edu.service;

import com.ccnu.edu.dao.EmployeeCrudRepository;
import com.ccnu.edu.domain.Employee;
import com.ccnu.edu.respository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


/**
 * Created by 董乐强 on 2017/11/29.
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeCrudRepository employeeCrudRepository;

    //为该方法加上事务操作
    @Transactional
    public void update(Integer id,Integer age){
        employeeRepository.upate(id,age);
    }

    @Transactional
    public void save(List<Employee> employees){
        employeeCrudRepository.save(employees);
    }
}
