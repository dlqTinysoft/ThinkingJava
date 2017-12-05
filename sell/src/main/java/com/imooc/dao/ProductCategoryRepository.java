package com.imooc.dao;

import com.imooc.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 董乐强 on 2017/12/5.
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

}
