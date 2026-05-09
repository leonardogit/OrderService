package com.leonardo.order_api.controller;

import com.leonardo.order_api.entity.Order;
import com.leonardo.order_api.repository.OrderRepository;
import com.leonardo.order_api.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return service.createOrder(order);
    }

    @GetMapping
    public List<Order> listOrders() {
        return service.listOrders();
    }
}
