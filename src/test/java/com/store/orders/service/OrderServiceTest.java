package com.store.orders.service;

import com.store.orders.dto.OrderResponse;
import com.store.orders.entity.Order;
import com.store.orders.entity.OrderItem;
import com.store.orders.mapper.OrderMapper;
import com.store.orders.repository.OrderRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    @Test
    void shouldReturnMappedOrders() {
        OrderRepository repository = mock(OrderRepository.class);

        Order order = new Order("ORD-1001");

        order.addItem(new OrderItem("Laptop", 1, new BigDecimal("1200.00")));

        when(repository.findAllByOrderByCreatedAtDescIdDesc()).thenReturn(List.of(order));

        OrderService service = new OrderService(repository, new OrderMapper());
        var responses = service.getOrders();

        assertThat(responses).hasSize(1);
        assertThat(responses.getFirst().products()).hasSize(1);
    }


    @Test
    void shouldReturnMappedOrder() {
        OrderRepository repository = mock(OrderRepository.class);

        Order order = new Order("ORD-1001");

        order.addItem(new OrderItem("Laptop", 1, new BigDecimal("1200.00")));

        when(repository.findById(1L)).thenReturn(Optional.of(order));

        OrderService service = new OrderService(repository, new OrderMapper());
        OrderResponse response = service.getOrderById(1L);

        assertThat(response.products()).hasSize(1);
    }


}
