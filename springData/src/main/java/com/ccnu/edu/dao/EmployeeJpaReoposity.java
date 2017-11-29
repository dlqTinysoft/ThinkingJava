package com.ccnu.edu.dao;

import com.ccnu.edu.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 董乐强 on 2017/11/29.
 */
public interface EmployeeJpaReoposity extends JpaRepository<Employee,Integer>{

}
