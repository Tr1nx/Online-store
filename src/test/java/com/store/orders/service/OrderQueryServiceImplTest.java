package com.store.orders.service;

import com.store.orders.entity.Order;
import com.store.orders.entity.OrderItem;
import com.store.orders.entity.OrderStatus;
import com.store.orders.mapper.OrderMapper;
import com.store.orders.repository.OrderRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderQueryServiceImplTest {

    @Test
    void shouldReturnMappedOrders() {
        OrderRepository repository = mock(OrderRepository.class);
        Order order = new Order("ORD-1001", OrderStatus.CREATED, LocalDateTime.parse("2026-10-09T10:30:00"));
        order.addItem(new OrderItem("Laptop", 1, new BigDecimal("1200.00")));
        when(repository.findAllByOrderByCreatedAtDesc()).thenReturn(List.of(order));

        OrderQueryService service = new OrderQueryServiceImpl(repository, new OrderMapper());

        assertThat(service.getOrders()).hasSize(1);
        assertThat(service.getOrders().get(0).products()).hasSize(1);
    }
}
