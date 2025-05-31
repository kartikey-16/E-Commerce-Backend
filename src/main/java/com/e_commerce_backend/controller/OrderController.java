package com.e_commerce_backend.controller;


import com.e_commerce_backend.payload.ApiResponse;
import com.e_commerce_backend.payload.OrderDto;
import com.e_commerce_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add/{productId}")
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto,
                                             @PathVariable Integer productId)
    {
        OrderDto addOrder = orderService.addOrder(orderDto, productId);
        return  new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable Integer orderId)
    {
        this.orderService.deleteOrder(orderId);
        return new ResponseEntity<>(new ApiResponse("Order deleted successfully" ,true), HttpStatus.OK);
    }


}
