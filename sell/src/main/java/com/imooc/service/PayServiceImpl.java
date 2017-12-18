package com.imooc.service;

import com.imooc.dto.OrderDTO;
import com.imooc.eunm.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.utils.JsonUtil;
import com.imooc.utils.MathUtils;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by 董乐强 on 2017/12/13.
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    @Autowired
    private BestPayServiceImpl bestPayService;
    @Autowired
    private OrderService orderService;
    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName("微信点餐订单");
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[微信支付] request={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("[微信支付] response={}",JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override//微信不断的回调这个方法
    public PayResponse notify(String notifyData) {
        //这个开发不安全

        //1.验证签名,是不是微信请求过来的
        //2.支付的状态，微信发通知了，通知有可能是错误，没有支付完成
        //3.支付的金额校验，非常重要
        //4.支付人（下单人 == 支付人）
        //bestPayService已经做过了
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("[微信异步通知]payResponse={}",JsonUtil.toJson(payResponse));
        //修改订单支付状态
        String orderId = payResponse.getOrderId();
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null) {
            log.error("[微信支付] 异步通知，订单不存在 ,orderId={}", payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //判断金额是否一致,注意用compareTo进行比较 还有精度问题 0.10 0.1 是相等的，但是比较不相等
        if(!MathUtils.equals(payResponse.getOrderAmount(),orderDTO.getOrderAmount().doubleValue())){
            log.error("[微信支付] 异步通知,订单金额不一致,orderId={},微信通知金额={}，系统金额={}",payResponse.getOrderId(),
                    payResponse.getOrderAmount(),
                    orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }

        orderService.paid(orderDTO);
        return payResponse;
    }

    /**
     * 退款
     * @param orderDTO
     */
    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("[微信退款] request={}",JsonUtil.toJson(refundResponse));
        return refundResponse;
    }
}


























