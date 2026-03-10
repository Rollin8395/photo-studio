package com.photo.controller;

import com.photo.dto.CreateOrderRequest;
import com.photo.entity.PrintOrder;
import com.photo.entity.enums.OrderStatus;
import com.photo.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{code}")
    public PrintOrder getOrder(@PathVariable String code) {

        return orderService.getOrderByCode(code);

    }

    @GetMapping
    public List<PrintOrder> getOrders() {

        return orderService.getAllOrders();

    }

    @PatchMapping("/{id}/status")
    public PrintOrder updateStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status
    ) {

        return orderService.updateStatus(id, status);

    }
}
