package com.imooc.domain;

import com.imooc.eunm.OrderStatusEnum;
import com.imooc.eunm.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 董乐强 on 2017/12/9.
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    @Id
    private String orderId;
    /**
     * 买家名字
     **/
    private String buyerName;
    //    买家电话
    private String buyerPhone;
    //    买家地址
    private String buyerAddress;
    //    买家的微信Openid
    private String buyerOpenid;
    //    订单总金额,这个字段需要后台进行运算的
    private BigDecimal orderAmount;
    /**
     * 新订单,默认状态为新订单，0就是新订单
     **/
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    /**
     * 支付状态，默认为等待支付,默认为0，未支付即等待支付
     **/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    //    订单创建时间
    private Date createTime;
    //    订单更新时间
    private Date updateTime;

    public OrderMaster() {
    }
}
