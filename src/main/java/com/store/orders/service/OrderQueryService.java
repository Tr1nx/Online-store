package com.store.orders.service;

import com.store.orders.dto.OrderResponse;

import java.util.List;

public interface OrderQueryService {

    List<OrderResponse> getOrders();
}
