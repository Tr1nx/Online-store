package com.store.orders.controller;

import com.store.orders.dto.OrderItemResponse;
import com.store.orders.dto.OrderResponse;
import com.store.orders.service.OrderQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderQueryService orderQueryService;

    @Test
    void shouldReturnOrders() throws Exception {
        when(orderQueryService.getOrders()).thenReturn(List.of(
            new OrderResponse(
                "ORD-1001",
                "CREATED",
                LocalDateTime.parse("2026-10-09T10:30:00"),
                List.of(new OrderItemResponse("Laptop", 1, new BigDecimal("1200.00")))
            )
        ));

        mockMvc.perform(get("/api/orders").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].orderNumber").value("ORD-1001"))
            .andExpect(jsonPath("$[0].products[0].name").value("Laptop"));
    }
}
