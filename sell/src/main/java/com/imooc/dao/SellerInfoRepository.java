package com.imooc.dao;

import com.imooc.domain.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 董乐强 on 2017/12/21.
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {
    //通过openid来查询用户的信息
    SellerInfo findByOpenid(String openid);

}
