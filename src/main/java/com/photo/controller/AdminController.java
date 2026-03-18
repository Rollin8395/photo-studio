package com.photo.controller;

import com.photo.entity.PrintOrder;
import com.photo.entity.enums.OrderStatus;
import com.photo.repository.PrintOrderRepository;
import org.springframework.web.bind.annotation.*;
import com.photo.service.OrderService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final PrintOrderRepository orderRepository;
    private final OrderService orderService;
    public AdminController(PrintOrderRepository orderRepository,OrderService orderService)
    {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    @GetMapping("/stats")
    public Map<String, Object> getStats() {

        List<PrintOrder> orders = orderRepository.findAll();

        double revenue = orders.stream()
                .mapToDouble(PrintOrder::getTotalPrice)
                .sum();

        long pending = orders.stream()
                .filter(o -> o.getStatus() != OrderStatus.COLLECTED)
                .count();

        Map<String, Object> stats = new HashMap<>();

        stats.put("totalOrders", orders.size());
        stats.put("totalRevenue", revenue);
        stats.put("pendingOrders", pending);

        return stats;
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
