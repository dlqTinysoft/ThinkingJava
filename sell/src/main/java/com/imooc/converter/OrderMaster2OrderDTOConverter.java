package com.imooc.converter;

import com.imooc.domain.OrderMaster;
import com.imooc.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 董乐强 on 2017/12/11.
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        //将orderMaster转为OrderDTO
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    //将orderMasterList转为List<OrderDTO>
    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        //使用lamb表达式，是一个新技术 java8以上才可以用
        List<OrderDTO> orderDTOList = orderMasterList.stream().map(e -> convert(e)).
                collect(Collectors.toList());
        return orderDTOList;
    }
}


