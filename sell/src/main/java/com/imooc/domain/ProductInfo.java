package com.imooc.domain;

import com.imooc.eunm.ProductStatusEnum;
import com.imooc.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 董乐强 on 2017/12/8.
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {
    @Id
    private String productId;
    //名字
    private String productName;
    //单价
    private BigDecimal productPrice;
    //库存
    private Integer productStock;
    //产品的描述
    private String productDescription;
    //产品图片的超链接
    private String productIcon;
    //产品的状态，0表示正常，1表示下架,默认是上架的
    private Integer productStatus = ProductStatusEnum.UP.getCode();
    //类目编号,和产品类别进行关联的
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;

    public ProductInfo() {
    }


    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }









}
