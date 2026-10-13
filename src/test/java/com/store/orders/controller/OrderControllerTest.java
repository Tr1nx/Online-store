package com.store.orders.controller;

import com.store.orders.dto.OrderItemResponse;
import com.store.orders.dto.OrderResponse;
import com.store.orders.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    OrderService orderService;

    @Test
    void shouldReturnOrders() throws Exception {
        OrderItemResponse item = new OrderItemResponse(
                "Hoodie",
                1,
                new BigDecimal("60.00")
        );

        OrderResponse response = new OrderResponse(
                "ORD-1001",
                "CREATED",
                LocalDateTime.parse("2026-10-09T10:30:00"),
                List.of(item)
        );

        when(orderService.getOrders()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/orders").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderNumber").value("ORD-1001"))
                .andExpect(jsonPath("$[0].products[0].name").value("Hoodie"));    }

    @Test
    void shouldReturnOrderById() throws Exception {
        OrderItemResponse item = new OrderItemResponse(
                "Shirt",
                1,
                new BigDecimal("15.00")
        );

        OrderResponse response = new OrderResponse(
                "ORD-1001",
                "CREATED",
                LocalDateTime.parse("2026-10-09T10:30:00"),
                List.of(item)
        );

        when(orderService.getOrderById(1L)).thenReturn(response);

        mockMvc.perform(get("/api/orders/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderNumber").value("ORD-1001"))
                .andExpect(jsonPath("$.products[0].name").value("Shirt"));    }
}
