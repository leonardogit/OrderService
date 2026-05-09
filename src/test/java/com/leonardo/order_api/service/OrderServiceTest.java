package com.leonardo.order_api.service;

import com.leonardo.order_api.entity.Order;
import com.leonardo.order_api.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @InjectMocks
    private OrderService service;

    private Order order;

    @BeforeEach
    void setUp(){
        order = new Order("Leonardo","Notebook",4500.00);
    }

    @Test
    void shouldCreateOrderSuccessfully(){
        when(repository.save(order)).thenReturn(order);

        Order savedOrder = service.createOrder(order);

        assertNotNull(savedOrder);
        assertEquals("Leonardo", savedOrder.getCustomer());
        verify(repository).save(order);
    }

    @Test
    void shouldListAllOrders() {
        List<Order> orders = List.of(order);

        when(repository.findAll()).thenReturn(orders);

        List<Order> result = service.listOrders();

        assertEquals(1, result.size());
        assertEquals("Notebook", result.get(0).getProduct());
        verify(repository).findAll();
    }


    @Test
    void shouldThrowExceptionWhenPriceIsInvalid() {
        Order invalidOrder = new Order("Leonardo", "Notebook", 0.0);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> service.createOrder(invalidOrder)
        );

        assertEquals("Price must be greater than zero", exception.getMessage());
        verify(repository, never()).save(any());
    }

}
