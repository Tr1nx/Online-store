package com.store.orders.service;

import com.store.orders.dto.OrderResponse;
import com.store.orders.mapper.OrderMapper;
import com.store.orders.repository.OrderRepository;
import com.store.shared.exception.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderResponse> getOrders() {
        return orderRepository.findAllByOrderByCreatedAtDescIdDesc()
            .stream()
            .map(orderMapper::toResponse)
            .toList();
    }

    public OrderResponse getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toResponse)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + id + " not found"));
    }
}
