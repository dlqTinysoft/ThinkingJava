package com.imooc.service;

import com.imooc.domain.SellerInfo;
import org.springframework.stereotype.Service;

/**
 * Created by 董乐强 on 2017/12/21.
 */
public interface SellerService {

    SellerInfo findSellerInfoByOpenid(String openid);
}
