package com.mohamed.microservice.order.service.service;

import com.mohamed.microservice.order.service.dto.request.CreateOrderRequestDto;
import com.mohamed.microservice.order.service.dto.response.CreateOrderResponseDto;
import com.mohamed.microservice.order.service.mapper.OrderMapper;
import com.mohamed.microservice.order.service.model.Order;
import com.mohamed.microservice.order.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    public CreateOrderResponseDto addOrder(CreateOrderRequestDto data) {
        Order order = this.orderMapper.toEntity(data);
        Order savedOrdered = this.orderRepository.save(order);
        return this.orderMapper.toCreateDto(savedOrdered);
    }


}
