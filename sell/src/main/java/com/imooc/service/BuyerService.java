package com.imooc.service;

import com.imooc.dto.OrderDTO;

/**
 * Created by 董乐强 on 2017/12/12.
 */
public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消订单
    OrderDTO cancleOrder(String openid,String orderId);
}
