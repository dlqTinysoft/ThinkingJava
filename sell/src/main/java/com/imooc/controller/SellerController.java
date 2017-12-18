package com.imooc.controller;

import com.imooc.dto.OrderDTO;
import com.imooc.eunm.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by 董乐强 on 2017/12/14.
 * 卖家端后台页面,列出所有订单列表
 * 在这里使用Controller不用使用RestController因为不需要和前端进行交互，不用返回json字符串
 */
@Controller
@RequestMapping("/order")
@Slf4j
public class SellerController {
    @Autowired
    private OrderService orderService;

    //获得所有订单列表，分页查询，springData提供了完美的封装
    //需要传递的是第几页，以及每页的大小。
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "2") Integer size,
                             Map<String, Object> map) {

        PageRequest request = new PageRequest(page - 1, size);
        //得到分页的数据,使用map集合来封装数据.
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);

        //查出来数据后，返回到order/list这个页面
        return new ModelAndView("order/list", map);
    }

    /**
     * @param orderId
     * @return
     */

    /**
     * 订单的取消操作
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/cancle")
    //取消订单，必须把订单的id给传过来。
    public ModelAndView cancle(@RequestParam("orderId") String orderId, Map<String, Object> map) {

        try {
            //首先查出该订单
            OrderDTO orderDTO = orderService.findOne(orderId);
            //然后取消该订单
            orderService.cancle(orderDTO);
        } catch (SellException e) {
            log.error("[卖家取消订单] 发送异常");
            //TODO有个小bug 抛file not foundException
            //取消失败跳到error页面
            map.put("msg", e.getMessage());
            map.put("url", "/sell/order/list");
            return new ModelAndView("common/error", map);
        }
        //取消成功跳转到success页面
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url", "/sell/order/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
        } catch (SellException e) {
            log.error("[卖家端查询订单详情] 发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("orderDTO", orderDTO);
        return new ModelAndView("order/detail", map);
    }

    //完结订单

    /**
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    //完结订单，必须传入订单的orderId
    public ModelAndView finished(@RequestParam("orderId") String orderId,
                                 Map<String, Object> map) {
        OrderDTO orderDTO = null;
        try {
            //首先查到orderDTO
            orderDTO = orderService.findOne(orderId);
            //然后完结订单
            orderService.finish(orderDTO);
        } catch (SellException e) {
            log.error("[卖家端完结订单] 发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("orderDTO", orderDTO);
        return new ModelAndView("common/success", map);
    }


}
