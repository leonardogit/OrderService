package com.leonardo.order_api.service;

import com.leonardo.order_api.entity.Order;
import com.leonardo.order_api.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/*
Marks business layer component.
Spring manages it automatically.
*/

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order createOrder(Order order) {

        if (order.getPrice() <= 0) {
            throw new RuntimeException("Price must be greater than zero");
        }

        return repository.save(order);
    }

    public List<Order> listOrders() {
        return repository.findAll();
    }

}
