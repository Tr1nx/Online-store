package com.store.orders.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
    String orderNumber,
    String status,
    LocalDateTime createdAt,
    List<OrderItemResponse> products
) {
}
