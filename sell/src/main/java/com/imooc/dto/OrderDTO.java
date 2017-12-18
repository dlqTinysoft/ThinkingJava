package com.imooc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.domain.OrderDetail;
import com.imooc.eunm.OrderStatusEnum;
import com.imooc.eunm.PayStatusEnum;
import com.imooc.utils.Date2LongSerializer;
import com.imooc.utils.EnumUtil;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by 董乐强 on 2017/12/9.
 * 这个类和OrderMaster字段完全一样，就是多了 List<OrderDetail> orderDetailList
 * 这个类是作为数据传输对象使用,不是entity对象,只在控制层和service层进行传输，持久层还是用OrderMaster
 * 在service层OrderDTO 必须转换为OrderMaster
 */
@Data
//@JsonSerialize(include = )
//当这个类中需要返回时，为null值的字段不返回
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;
    //    买家名称
    private String buyerName;
    //    买家电话
    private String buyerPhone;
    //    买家地址
    private String buyerAddress;
    //   买家的openId
    private String buyerOpenid;
    //    订单总金额
    private BigDecimal orderAmount;
    /**
     * 新订单
     **/
    private Integer orderStatus;
    /**
     * 支付状态，默认为等待支付,0为等待支付
     **/
    private Integer payStatus;
    //将时间转换为秒的简单用法
    @JsonSerialize(using= Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using =Date2LongSerializer.class)
    private Date updateTime;
    //    关联订单详情
    List<OrderDetail> orderDetailList;

    @JsonIgnore//忽略返回的json
    public OrderStatusEnum getOrderStatusEnum(){
        //通过code值来获取枚举
        //orderStatus,注意这样的写法非常好,通过订单的orderStatus来获取枚举
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }
    @JsonIgnore//忽略返回的json
    public PayStatusEnum getPayStatusEnum(){
          // payStatus的值获取枚举,payStatus的状态来获取枚举
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
