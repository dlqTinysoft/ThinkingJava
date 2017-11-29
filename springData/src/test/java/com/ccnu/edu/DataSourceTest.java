package com.ccnu.edu;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by 董乐强 on 2017/11/29.
 */
public class DataSourceTest {
    private ApplicationContext ctx = null;

    @Before
    public void init(){
        ctx = new ClassPathXmlApplicationContext("beans.xml");
    }
    @After
    public void destory(){
        ctx=null;
    }

    @Test
    public void testDatasource(){
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        Assert.assertNotNull(dataSource);
    }

    @Test
    public void testJdbcTemplate(){
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        Assert.assertNotNull(jdbcTemplate);
    }












}
