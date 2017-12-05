package com.imooc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 董乐强 on 2017/12/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogTest {
    private final Logger logger = LoggerFactory.getLogger(LogTest.class);
    @Test
    public void test1(){
        String name= "imooc";
        String password = "123456";
        logger.info("name:{},password:{}",name,password);
        logger.info("name:{},password:{}",name,password);
        logger.debug("name:{},password:{}",name,password);
    }
}
