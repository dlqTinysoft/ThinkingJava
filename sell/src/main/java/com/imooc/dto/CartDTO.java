package com.imooc.dto;

import lombok.Data;

/**
 * 购物车
 * Created by 董乐强 on 2017/12/10.
 * 从前端传过来的信息，在controller和service进行传递，不会传到dao层，起到传输作用
 */
@Data
public class CartDTO {
    //商品Id
    private String productId;
    //商品数量
    private Integer productQuantity;
    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
