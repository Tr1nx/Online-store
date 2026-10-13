package com.store.orders.mapper;

import com.store.orders.dto.OrderResponse;
import com.store.orders.entity.Order;
import com.store.orders.entity.OrderItem;
import com.store.orders.entity.OrderStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class OrderMapperTest {

    private final OrderMapper orderMapper = new OrderMapper();

    @Test
    void shouldMapOrderToResponse() {
        Order order = new Order("ORD-1001");

        order.addItem(new OrderItem("Laptop", 1, new BigDecimal("1200.00")));

        order.addItem(new OrderItem("Mouse", 2, new BigDecimal("25.00")));

        OrderResponse response = orderMapper.toResponse(order);

        assertThat(response.orderNumber()).isEqualTo("ORD-1001");
        assertThat(response.status()).isEqualTo("CREATED");
        assertThat(response.products()).hasSize(2);
        assertThat(response.products().getFirst().name()).isEqualTo("Laptop");
    }
}
