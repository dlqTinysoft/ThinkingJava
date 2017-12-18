package com.imooc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imooc.domain.ProductInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 董乐强 on 2017/12/8.
 */
@Data
public class ProductVo {
    //类目的名字
    @JsonProperty("name")
    private String categoryName;
    //类目的类型
    @JsonProperty("type")
    private Integer categoryType;
    //这个类目下的所有产品
    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList = new ArrayList<>();
}

