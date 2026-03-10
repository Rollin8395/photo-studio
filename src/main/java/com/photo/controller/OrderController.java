package com.photo.controller;

import com.photo.dto.CreateOrderRequest;
import com.photo.entity.PrintOrder;
import com.photo.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public PrintOrder createOrder(@RequestBody CreateOrderRequest request) {

        return orderService.createOrder(request);

    }
}
