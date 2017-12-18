package com.imooc.service;

import com.imooc.domain.ProductCategory;

import java.util.List;

/**
 * Created by 董乐强 on 2017/12/8.
 */
public interface CategoryService {
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes);

    ProductCategory save(ProductCategory productCategory);

}
