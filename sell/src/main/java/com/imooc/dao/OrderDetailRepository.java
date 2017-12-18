package com.imooc.dao;

import com.imooc.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 董乐强 on 2017/12/9.
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    //通过订单的id查询详情,返回多条OrderDetail
    List<OrderDetail> findByOrderId(String oderId);
}
