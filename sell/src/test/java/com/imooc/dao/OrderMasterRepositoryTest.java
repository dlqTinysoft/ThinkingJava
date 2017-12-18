package com.imooc.dao;

import com.imooc.domain.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by 董乐强 on 2017/12/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;
    private final String openId="110110";
    @Test
    public void findByBuyerOpenId() throws Exception {
        //传入分页的参数,第一个参数是从第0页开始，第二个参数是这一页有多少项
        PageRequest request = new PageRequest(1,2);
        Page<OrderMaster> result= repository.findByBuyerOpenid(openId,request);
        System.out.println(result.getTotalPages());

    }

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123458");
        orderMaster.setBuyerName("郑玉妮");
        orderMaster.setBuyerPhone("12345678912");
        orderMaster.setBuyerAddress("华中师范大学");
        orderMaster.setBuyerOpenid("110110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        repository.save(orderMaster);
    }















}