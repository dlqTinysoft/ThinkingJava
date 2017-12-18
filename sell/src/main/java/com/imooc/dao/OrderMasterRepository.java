package com.imooc.dao;

import com.imooc.domain.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 董乐强 on 2017/12/9.
 */
public interface OrderMasterRepository  extends JpaRepository<OrderMaster,String>{
    //按照买家的openId来进行查询
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenId, Pageable pageable);
}
