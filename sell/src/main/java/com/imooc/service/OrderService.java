package com.imooc.service;
import com.imooc.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by 董乐强 on 2017/12/9.
 * 订单表的service层相当复杂，涉及的业务逻辑之多，同事还涉及到并发问题，同时这也是整个应用的核心部分
 * 核心中的核心
 */
public interface OrderService {

    /**
     * 创建订单,不用orderMaster,让OrderMaster和orderDetail相关联
     * 用OrderDTO代替orderMaster，Data transfer object 数据传输对象
     * OrderDTO和OrderMaster一样，只是加了和OrderDetail对象的关联
     * OrderDTO在controller和service进行数据传输，OrderMaster在dao层和数据库交互，在service层，最终会把OrderDTO转换为OrderMaster
     * 注意OrderDTO和OrderMaster有质的区别，两个对象完全不一样的
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 查询单个订单
     */
    OrderDTO findOne(String orderDTO);

    /**
     * 查询订单列表
     */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     */
    OrderDTO cancle(OrderDTO orderDTO);

    /**
     * 完结订单
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * 支付订单
     */
    OrderDTO paid(OrderDTO orderDTO);
    /**
     * 查询订单列表
     */
    Page<OrderDTO> findList(Pageable pageable);

}
