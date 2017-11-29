package com.ccnu.edu.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 董乐强 on 2017/11/29.
 */
public class SpringDataTest {
    private ApplicationContext ctx=null;
    @Before
    public void init(){
        ctx = new ClassPathXmlApplicationContext("new_beans.xml");
    }
    @After
    public void destory(){
        ctx=null;
    }

    @Test
    public void testEntityManagerFactory(){

    }






}
