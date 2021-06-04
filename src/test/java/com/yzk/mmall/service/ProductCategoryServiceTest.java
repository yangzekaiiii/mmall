package com.yzk.mmall.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductCategoryServiceTest {

    @Autowired
    ProductCategoryService productCategoryService;

    @Test
    void test(){
        productCategoryService.getAllProductCategoryVO().forEach(System.out::println);
    }
}