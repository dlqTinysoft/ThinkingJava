package com.imooc.dao;

import com.imooc.domain.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 董乐强 on 2017/12/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOneTest(){
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        System.out.println(productCategory);
    }

    @Test
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        //productCategory.setCategoryId(2);
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(6);
        productCategoryRepository.save(productCategory);
    }

    @Test
    public void updateTest(){
       ProductCategory productCategory = productCategoryRepository.findOne(5);
       productCategory.setCategoryType(10);
       productCategoryRepository.save(productCategory);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> types = Arrays.asList(2,3);
        List<ProductCategory> productCategories = productCategoryRepository.findByCategoryTypeIn(types);
        System.out.println(productCategories.get(0));
    }


}