package com.store.orders.repository;

import com.store.orders.entity.Order;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = "items")
    List<Order> findAllByOrderByCreatedAtDescIdDesc();

    @EntityGraph(attributePaths = "items")
    @NonNull
    Optional<Order> findById(@NonNull Long id);
}
