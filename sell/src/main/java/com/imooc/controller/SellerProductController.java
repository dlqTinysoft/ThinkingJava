package com.imooc.controller;

import com.imooc.domain.ProductCategory;
import com.imooc.domain.ProductInfo;
import com.imooc.dto.OrderDTO;
import com.imooc.exception.SellException;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 卖家端商品
 * Created by 董乐强 on 2017/12/17.
 */
@Controller
@RequestMapping("/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    //得到所有商品列表
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "2") Integer size,
                             Map<String, Object> map) {
        //这里请求下标是从0开始的
        PageRequest request = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    //新增商品
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                              Map<String,Object> map){

        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo",productInfo);
        }
        List<ProductCategory> categoryList =  categoryService.findAll();
        map.put("categoryList",categoryList);
        //跳转到修改页面，注意修改和新增用到的是同一个界面
        return new ModelAndView("product/index",map);
    }

    //上架
    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam(value="productId") String productId,
                               Map<String,Object> map){
        try {
            productService.onSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","product/list");
            return new ModelAndView("common/error",map);
        }

        map.put("url","product/list");
        return new ModelAndView("common/success",map);
    }

    //下架
    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam(value="productId") String productId,
                               Map<String,Object> map){
        try {
            productService.offSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","product/list");
            return new ModelAndView("common/error",map);
        }

        map.put("url","product/list");
        return new ModelAndView("common/success",map);
    }

}
