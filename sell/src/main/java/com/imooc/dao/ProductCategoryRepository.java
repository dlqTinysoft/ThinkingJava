package com.imooc.dao;

import com.imooc.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 董乐强 on 2017/12/5.
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    //通过categoryType查询所有ProductCategory;
    //categoryType这个字段是唯一的
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
