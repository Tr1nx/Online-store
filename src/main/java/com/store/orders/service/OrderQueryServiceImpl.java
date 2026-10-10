package com.store.orders.service;

import com.store.orders.dto.OrderResponse;
import com.store.orders.mapper.OrderMapper;
import com.store.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderQueryServiceImpl implements OrderQueryService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderQueryServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderResponse> getOrders() {
        return orderRepository.findAllByOrderByCreatedAtDesc()
            .stream()
            .map(orderMapper::toResponse)
            .toList();
    }
}
