package com.mohamed.microservice.order.service.mapper;

import com.mohamed.microservice.order.service.dto.request.CreateOrderRequestDto;
import com.mohamed.microservice.order.service.dto.response.CreateOrderResponseDto;
import com.mohamed.microservice.order.service.model.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderMapper {

    public Order toEntity(CreateOrderRequestDto data){
        Order order = new Order();
        order.setId(Long.valueOf(UUID.randomUUID().toString()));
        order.setOrderNumber(data.getOrderNumber());
        order.setSkuCode(data.getSkuCode());
        order.setPrice(data.getPrice());
        order.setQuantity(data.getQuantity());
        return order;
    }

    public CreateOrderResponseDto toCreateDto(Order order){
        return new CreateOrderResponseDto(
                order.getId(),
                order.getOrderNumber(),
                order.getSkuCode(),
                order.getPrice(),
                order.getQuantity(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }


}
