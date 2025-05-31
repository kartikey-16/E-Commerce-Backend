package com.e_commerce_backend.service.impl;

import com.e_commerce_backend.entity.Order;
import com.e_commerce_backend.entity.Product;
import com.e_commerce_backend.exception.ResourceNotFoundException;
import com.e_commerce_backend.payload.OrderDto;
import com.e_commerce_backend.payload.ProductDto;
import com.e_commerce_backend.repository.OrderRepo;
import com.e_commerce_backend.repository.ProductRepo;
import com.e_commerce_backend.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ModelMapper modelMapper;


        @Override
        public OrderDto addOrder(OrderDto orderDto, Integer productId) {

            Product product = this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

            // Create new Order and add product
            Order order = new Order();
            order.setItems(List.of(product));  // Or new ArrayList<>(List.of(product))
            // createdAt is automatically set by @PrePersist

            // Save order
            Order savedOrder = this.orderRepo.save(order);
            return this.orderToDto(savedOrder);

        }

    @Override
    public void deleteOrder(Integer orderId) {

        Order order = this.orderRepo.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
        this.orderRepo.delete(order);
    }

    public OrderDto orderToDto(Order order)
    {
        OrderDto orderDto  = this.modelMapper.map(order, OrderDto.class);
        return orderDto;
    }

    public Order dtoToOrder(OrderDto orderDto)
    {
        Order order = this.modelMapper.map(orderDto, Order.class);
        return order;
    }
}
