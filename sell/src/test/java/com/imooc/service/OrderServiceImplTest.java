package com.imooc.service;

import com.imooc.domain.OrderDetail;
import com.imooc.dto.OrderDTO;
import com.imooc.eunm.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 董乐强 on 2017/12/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;
    @Test
    public void create() throws Exception {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("董乐强");
        orderDTO.setBuyerAddress("华中师范大学");
        orderDTO.setBuyerPhone("18062584539");
        orderDTO.setBuyerOpenid("110110");

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("123456");
        orderDetail1.setProductQuantity(1);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("123457");
        orderDetail2.setProductQuantity(2);

        orderDetailList.add(orderDetail1);
        orderDetailList.add(orderDetail2);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        log.info("[创建订单] result={}", result);
    }

    @Test
    public void findOne() throws Exception {
        OrderDTO result = orderService.findOne("1512957346736904201");
        log.info("查询订单 result={}",result);
        Assert.assertEquals("1512957346736904201",result.getOrderId());
    }

    @Test
    public void findList() throws Exception {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList("110110",pageRequest);
        log.info("orderDTOPage={}",orderDTOPage);
    }

    @Test
    public void cancle() throws Exception {
        OrderDTO result = orderService.findOne("1512957346736904201");
        orderService.cancle(result);
    }

    @Test
    public void finish() throws Exception {
        OrderDTO result = orderService.findOne("1512957346736904201");
        orderService.finish(result);
    }

    @Test
    public void paid() throws Exception {
        OrderDTO result = orderService.findOne("1512957346736904201");
        orderService.paid(result);
    }

}