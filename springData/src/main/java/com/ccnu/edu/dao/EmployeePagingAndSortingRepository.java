package com.ccnu.edu.dao;

import com.ccnu.edu.domain.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by 董乐强 on 2017/11/29.
 */
public interface EmployeePagingAndSortingRepository extends PagingAndSortingRepository<Employee,Integer> {
}

