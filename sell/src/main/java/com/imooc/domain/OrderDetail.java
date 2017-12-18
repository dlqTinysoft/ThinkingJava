package com.imooc.domain;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by 董乐强 on 2017/12/9.
 */
@Entity
@Data
public class OrderDetail {
    /**
     * 订单详情Id
     */
    @Id
    private String detailId;
    /**
     * 订单id,就是通过订单Id和订单进行关联的
     **/
    private String orderId;
    /**
     * 商品productId,从而能够和商品进行关联
     */
    private String productId;
    /**
     * 商品名称
     **/
    private String productName;
    /**
     * 商品的价格
     */
    private BigDecimal productPrice;
    /**
     * 商品数量
     **/
    private Integer productQuantity;
    /**
     * 商品小图
     **/
    private String productIcon;

    public OrderDetail() {
    }
}
