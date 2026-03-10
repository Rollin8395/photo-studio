package com.photo.service;

import com.photo.dto.CreateOrderRequest;
import com.photo.entity.*;
import com.photo.entity.enums.OrderStatus;
import com.photo.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final CustomerRepository customerRepository;
    private final PhotoRepository photoRepository;
    private final PrintOrderRepository orderRepository;
    private final PrintOrderItemRepository itemRepository;

    public OrderService(
            CustomerRepository customerRepository,
            PhotoRepository photoRepository,
            PrintOrderRepository orderRepository,
            PrintOrderItemRepository itemRepository) {

        this.customerRepository = customerRepository;
        this.photoRepository = photoRepository;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    public PrintOrder createOrder(CreateOrderRequest request) {

        Customer customer = customerRepository
                .findByPhone(request.getPhone())
                .orElseGet(() -> {
                    Customer c = new Customer();
                    c.setPhone(request.getPhone());
                    return customerRepository.save(c);
                });

        PhotoAsset photo = photoRepository
                .findById(request.getPhotoId())
                .orElseThrow();

        PrintOrder order = new PrintOrder();
        order.setOrderCode(UUID.randomUUID().toString().substring(0, 6));
        order.setCustomer(customer);
        order.setStatus(OrderStatus.NEW);
        order.setTotalPrice(0.0);

        order = orderRepository.save(order);

        double price = calculatePrice(request.getPrintSize(), request.getQuantity());

        PrintOrderItem item = new PrintOrderItem();
        item.setOrder(order);
        item.setPhoto(photo);
        item.setPrintSize(request.getPrintSize());
        item.setPaperType(request.getPaperType());
        item.setQuantity(request.getQuantity());
        item.setPrice(price);

        itemRepository.save(item);

        order.setTotalPrice(price);
        orderRepository.save(order);

        return order;
    }

    private double calculatePrice(Enum size, int qty) {

        double base;

        switch (size.toString()) {

            case "SIZE_4X6":
                base = 1.0;
                break;

            case "SIZE_5X7":
                base = 1.5;
                break;

            case "A4":
                base = 3.0;
                break;

            default:
                base = 1.0;
        }

        return base * qty;
    }

    public PrintOrder getOrderByCode(String code) {

        return orderRepository
                .findByOrderCode(code)
                .orElseThrow(() -> new RuntimeException("Order not found"));

    }
    //Admin service view all
    public List<PrintOrder> getAllOrders() {
        return orderRepository.findAll();
    }


    public PrintOrder updateStatus(Long orderId, OrderStatus status) {

        PrintOrder order = orderRepository
                .findById(orderId)
                .orElseThrow();

        order.setStatus(status);

        return orderRepository.save(order);
    }

}
