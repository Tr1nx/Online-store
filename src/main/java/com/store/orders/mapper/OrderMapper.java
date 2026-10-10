package com.store.orders.mapper;

import com.store.orders.dto.OrderItemResponse;
import com.store.orders.dto.OrderResponse;
import com.store.orders.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponse toResponse(Order order) {
        return new OrderResponse(
            order.getOrderNumber(),
            order.getStatus().name(),
            order.getCreatedAt(),
            order.getItems().stream()
                .map(item -> new OrderItemResponse(item.getName(), item.getQuantity(), item.getUnitPrice()))
                .toList()
        );
    }
}
