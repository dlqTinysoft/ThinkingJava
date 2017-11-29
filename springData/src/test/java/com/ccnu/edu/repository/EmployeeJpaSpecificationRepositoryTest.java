package com.ccnu.edu.repository;

import com.ccnu.edu.dao.EmployeeJpaReoposity;
import com.ccnu.edu.dao.EmployeeJpaSpecificationRepository;
import com.ccnu.edu.domain.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

/**
 * Created by 董乐强 on 2017/11/29.
 */
public class EmployeeJpaSpecificationRepositoryTest {
    private ApplicationContext ctx=null;
    private EmployeeJpaSpecificationRepository employeeJpaSpecificationRepository=null;
    @Before
    public void init(){
        ctx = new ClassPathXmlApplicationContext("new_beans.xml");
        employeeJpaSpecificationRepository=ctx.getBean(EmployeeJpaSpecificationRepository.class);
    }
    @After
    public void destory(){
        ctx=null;
    }

    /**
     * 1.分页
     * 2.排序
     * 3.查询条件 age>50
     */
    @Test
    public void testQuery(){
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"id");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(0,9,sort);

        Specification<Employee> specification = new Specification<Employee>() {
            /**
             *
             * @param root Employee
             * @param query 添加查询条件
             * @param cb 构建的是Predicate
             * @return
             */
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path path = root.get("age");
                return cb.gt(path,50);
            }
        };

        Page<Employee> page = employeeJpaSpecificationRepository.findAll(specification,pageable);

        System.out.println("总页数: "+page.getTotalPages());

        System.out.println("总记录数: "+page.getTotalElements());

        System.out.println("当前第几页: "+(page.getNumber()+1));

        System.out.println("当前页面的集合: "+page.getContent());

        System.out.println("查询当前的记录数: "+page.getNumberOfElements());
    }
}
