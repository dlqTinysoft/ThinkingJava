package com.imooc.controller;

import com.imooc.dto.OrderDTO;
import com.imooc.eunm.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import com.imooc.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.rest.type.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by 董乐强 on 2017/12/13.
 */
@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl") String returnUrl,
                               Map<String,Object> map){

        //1.查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null)
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        //2.发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse",payResponse);
        //支付成功后跳转页面
        map.put("returnUrl",returnUrl);
        return new ModelAndView("pay/create",map);
    }

    @PostMapping("/notify")//支付完成后，异步通知就过类了
    //微信支付完成后，会回调这个接口
    public ModelAndView notify(@RequestBody String notifyData){
          payService.notify(notifyData);
          //返回给微信处理结果
        return new ModelAndView("pay/success");
    }
}
