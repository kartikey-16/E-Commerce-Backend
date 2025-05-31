package com.e_commerce_backend.service;

import com.e_commerce_backend.payload.OrderDto;

public interface OrderService {

    OrderDto addOrder(OrderDto orderDto , Integer productId);
    void deleteOrder(Integer orderId);
//
//    OrderDto getOrderDetail(Integer orderId);
}
