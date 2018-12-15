package com.infinity.order.service;

import com.infinity.order.pojo.Item;
import com.infinity.order.pojo.Order;
import com.infinity.order.pojo.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    public static final Map<String, Order> MAP = new HashMap<>();

    @Autowired
    private ItemService itemService;

    static {
        Order order = Order.builder()
                .orderId("59193738268961441")
                .createDate(new Date())
                .userId(1L).build();
        order.setUpdateDate(order.getCreateDate());

        List<OrderDetail> orderDetails = new ArrayList<>();

        Item item = Item.builder().id(1L).build();
        orderDetails.add(new OrderDetail(order.getOrderId(), item));
        order.setOrderDetailList(orderDetails);

        MAP.put(order.getOrderId(), order);
    }

    public Order queryOrderById(String orderId) {

        Order order = MAP.get(orderId);
        if (null == order) {
            return null;
        }

        List<OrderDetail> orderDetails = order.getOrderDetailList();

        for (OrderDetail orderDetail : orderDetails) {
            Item item = itemService.queryItemById(orderDetail.getItem().getId());
            if (null == item) {
                continue;
            }
            orderDetail.setItem(item);
        }

        return order;
    }
}
