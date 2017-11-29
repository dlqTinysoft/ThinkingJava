package com.ccnu.edu.dao;

import com.ccnu.edu.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by 董乐强 on 2017/11/29.
 */
public interface EmployeeJpaSpecificationRepository extends JpaRepository<Employee,Integer> ,JpaSpecificationExecutor<Employee> {
}
