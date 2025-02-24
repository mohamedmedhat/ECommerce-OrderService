package com.mohamed.microservice.order.service.controller;

import com.mohamed.microservice.order.service.dto.request.CreateOrderRequestDto;
import com.mohamed.microservice.order.service.dto.response.CreateOrderResponseDto;
import com.mohamed.microservice.order.service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private OrderService orderService;

    @PostMapping
    public CreateOrderResponseDto createOrder(CreateOrderRequestDto data) {
        return this.orderService.addOrder(data);
    }
}
