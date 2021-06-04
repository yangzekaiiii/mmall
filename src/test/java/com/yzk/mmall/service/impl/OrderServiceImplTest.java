package com.yzk.mmall.service.impl;

import com.yzk.mmall.entity.OrderDetail;
import com.yzk.mmall.entity.Orders;
import com.yzk.mmall.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderService service;

    @Test
    void test(){
        Orders orders = new Orders();
        orders.setUserId(1);
        orders.setLoginName("1");
        orders.setUserAddress("asd");
        orders.setCost(100.0f);
        service.save(orders);
    }

}