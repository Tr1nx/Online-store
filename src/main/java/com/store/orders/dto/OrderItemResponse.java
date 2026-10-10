package com.store.orders.dto;

import java.math.BigDecimal;

public record OrderItemResponse(
    String name,
    Integer quantity,
    BigDecimal unitPrice
) {
}
