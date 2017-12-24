package com.imooc.form;

import com.imooc.eunm.ProductStatusEnum;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 董乐强 on 2017/12/19.
 */
@Data
public class ProductForm {
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
    //类目编号,和产品类别进行关联的
    private Integer categoryType;

}
