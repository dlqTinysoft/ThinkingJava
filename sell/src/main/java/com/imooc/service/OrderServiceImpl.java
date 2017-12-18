package com.imooc.service;

import com.imooc.converter.OrderMaster2OrderDTOConverter;
import com.imooc.dao.OrderDetailRepository;
import com.imooc.dao.OrderMasterRepository;
import com.imooc.domain.OrderDetail;
import com.imooc.domain.OrderMaster;
import com.imooc.domain.ProductInfo;
import com.imooc.dto.CartDTO;
import com.imooc.dto.OrderDTO;
import com.imooc.eunm.OrderStatusEnum;
import com.imooc.eunm.PayStatusEnum;
import com.imooc.eunm.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 董乐强 on 2017/12/9.
 * OrderService依赖ProductService OrderDetailRepository OrderMasterRepository这个三个类
 * 才能完成OrderService这层的功能，很复杂，是整个应用的核心部分
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private PayService payService;

    @Transactional//必须加上事务
    @Override
    /**
     * 用户下订单，底层其实就是处理三步
     * 1.查询商品信息
     * 2.计算总金额
     * 3.减库存
     */
    public OrderDTO create(OrderDTO orderDTO) {
        //orderDTO中的orderDetail中只有商品的id和name
        //为订单生产一个Id
        String orderId = KeyUtil.genUniqueKey();
        //orderId返回的时候要用到生成的订单id,这个订单id必须传回去
        orderDTO.setOrderId(orderId);
//        List<CartDTO> cartDTOList = new ArrayList<>();
        //1.查询商品，数量 价格 等信息
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            //根据productId，来查询ProductInfo
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null)
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            //是否数量足够,其实不需要，放到扣库存的地方来做
            //2.计算订单总价,详情总价相加
            //价格必须从productInfo里面获取
            orderAmount=productInfo.getProductPrice().
                    multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //订单详情入库
            //对象的属性copy,用属性copy时，属性值是null时，也会copy
            //必须先copy,后复制值，就ok,这个问题就是从OrderDTO变为OrderMaster的转化过程，数据不能丢失
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            //把产品的信息封装到orderDetail
            BeanUtils.copyProperties(productInfo,orderDetail);
            //保存到数据库
            orderDetailRepository.save(orderDetail);
//            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),
//                    orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);
        }

        //3.写入订单数据库 orderMaster ，下面几行代码就是把OrderDTO转化为OrderMaster
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        //4.扣库存,必须同步，否则会超买的情况，怎么解决
        //使用redis的锁机制
        //使用lamb表达式确实很方便,不用for循环了
        List<CartDTO> cartDTOList =
        orderDTO.getOrderDetailList().stream().
                map(e -> new CartDTO(e.getProductId(),e.getProductQuantity())).
                collect(Collectors.toList());
        productService.decreaseStack(cartDTOList);
        return orderDTO;
    }

    /**
     * 主要是从数据查询出订单，以及该订单下的所有商品，然后封装到OrderDTO返回
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster == null)
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList))
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    /**
     * 分页查询数据
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    @Transactional
    @Override
    public OrderDTO cancle(OrderDTO orderDTO) {

        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[取消订单]订单状态不正确,orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
         orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
         BeanUtils.copyProperties(orderDTO,orderMaster);
         OrderMaster updateResult = orderMasterRepository.save(orderMaster);
         if(updateResult == null) {
             log.error("[取消订单]更新失败,orderMaster={}", orderMaster);
             throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
         }
        //减库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
             log.error("[取消订单]订单无商品详情,orderDTO={}",orderDTO);
             throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().
                map(e->new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStack(cartDTOList);
        //如果已支付，需要退款
        if(orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //退款
            payService.refund(orderDTO);
        }

        return orderDTO;
    }

    @Transactional
    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        //1.判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[完结订单]订单状态不正确,orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.修改状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("[完结订单] 更新失败,orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Transactional
    @Override
    public OrderDTO paid(OrderDTO orderDTO) {

        //1.判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[订单支付成功]订单状态不正确,orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("[订单支付成功] 订单支付状态不正确,orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //3.修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("[订单支付成功] 更新失败,orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());

    }
}
