package com.imooc.service;

import com.imooc.domain.ProductInfo;
import com.imooc.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by 董乐强 on 2017/12/8.
 */
public interface ProductService {
    /**
     * 根据商品的id来查询商品
     *
     * @param productId
     * @return
     */
    ProductInfo findOne(String productId);

    /**
     * 查询上架的所有商品
     *
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 分页查询所有商品
     *
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 添加商品信息
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);

    //加库存 CartDTO封装的是从前端传过来的数据 ,只用productId和数量两个字段
    void increaseStack(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStack(List<CartDTO> cartDTOList);

    //上架,就是修改产品的状态
    ProductInfo onSale(String productId);

    //下架,就是修改产品的状态
    ProductInfo offSale(String productId);

}
