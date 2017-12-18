package com.imooc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品详情
 * Created by 董乐强 on 2017/12/8.
 */
@Data
public class ProductInfoVo {

    //产品的id
    @JsonProperty("id")
    private String productId;
    //产品的名字
    @JsonProperty("name")
    private String productName;
    //产品的价格
    @JsonProperty("price")
    private BigDecimal productPrice;
    //产品的描述
    @JsonProperty("description")
    private String productDescription;
    //产品的图片
    @JsonProperty("icon")
    private String productIcon;
}
