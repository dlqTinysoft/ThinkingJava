package com.imooc.service;

import com.imooc.dao.ProductInfoRepository;
import com.imooc.domain.ProductInfo;
import com.imooc.dto.CartDTO;
import com.imooc.eunm.ProductStatusEnum;
import com.imooc.eunm.ResultEnum;
import com.imooc.exception.SellException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by 董乐强 on 2017/12/9.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Transactional //必须加上事务处理机制
    @Override
    public void increaseStack(List<CartDTO> cartDTOList) {
        //1.根据商品的id,查询商品。
        //2.对商品的库存进行增加操作
        for (CartDTO cartDTO : cartDTOList) {
            String productId = cartDTO.getProductId();
            //得到商品的信息，对商品进行更新
            ProductInfo productInfo = repository.findOne(productId);
            if (productInfo == null)
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            productInfo.setProductStock(cartDTO.getProductQuantity() + productInfo.getProductStock());
            repository.save(productInfo);
        }

    }

    @Transactional //必须加上事务处理机制
    @Override
    public void decreaseStack(List<CartDTO> cartDTOList) {
        //同加库存的逻辑一样
        for (CartDTO cartDTO : cartDTOList) {
            String productId = cartDTO.getProductId();
            ProductInfo productInfo = repository.findOne(productId);

            if (productInfo == null)
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0)
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = repository.findOne(productId);
        if(productInfo == null )
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        if(productInfo.getProductStatus() == ProductStatusEnum.UP.getCode())
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return repository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = repository.findOne(productId);
        if(productInfo == null )
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        if(productInfo.getProductStatus() == ProductStatusEnum.DOWN.getCode())
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return repository.save(productInfo);
    }
}
