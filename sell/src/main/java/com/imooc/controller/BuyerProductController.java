package com.imooc.controller;

import com.imooc.domain.ProductCategory;
import com.imooc.domain.ProductInfo;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.vo.ProductInfoVo;
import com.imooc.vo.ProductVo;
import com.imooc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 买家端商品展示，包括类别和商品信息
 * Created by 董乐强 on 2017/12/8.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVo list(){
        //1.查询所有的上架商品
         List<ProductInfo> productInfoList = productService.findUpAll();
        //2.查询类目(一次查询)
        List<Integer> categoryTypes = new ArrayList<>();
        for(ProductInfo productInfo :productInfoList)
            categoryTypes.add(productInfo.getCategoryType());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypes);
        //3.数据拼装
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        List<ProductVo> data = new ArrayList<>();
        resultVo.setData(data);
        ProductVo productVo = null;
        ProductInfoVo productInfoVo = null;
        List<ProductInfoVo> productInfoVoList=null;
        for(ProductCategory productCategory:productCategoryList ){
            productVo=new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());
            //这个类目下的所有上架商品
            productInfoVoList = new ArrayList<>();
            for(ProductInfo productInfo :productInfoList)
                if(productInfo.getCategoryType() ==productCategory.getCategoryType()) {
                    productInfoVo = new ProductInfoVo();
                    productInfoVo.setProductId(productInfo.getProductId());
                    productInfoVo.setProductName(productInfo.getProductName());
                    productInfoVo.setProductPrice(productInfo.getProductPrice());
                    productInfoVo.setProductDescription(productInfo.getProductDescription());
                    productInfoVo.setProductIcon(productInfo.getProductIcon());
                    productInfoVoList.add(productInfoVo);
                }
            productVo.setProductInfoVoList(productInfoVoList);
            data.add(productVo);
        }
        return resultVo;
    }
}
