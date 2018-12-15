package com.infinity.order.controller;

import com.infinity.order.pojo.Order;
import com.infinity.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/{orderId}")
    public Order queryOrderById(@PathVariable("orderId") String orderId) {

        return orderService.queryOrderById(orderId);
    }
}
