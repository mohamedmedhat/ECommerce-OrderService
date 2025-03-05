package com.mohamed.microservice.order.service.service;

import com.mohamed.microservice.order.service.client.InventoryClient;
import org.springframework.kafka.core.KafkaTemplate;
import com.mohamed.microservice.order.service.dto.request.CreateOrderRequestDto;
import com.mohamed.microservice.order.service.dto.response.CreateOrderResponseDto;
import com.mohamed.microservice.order.service.event.OrderPlacedEvent;
import com.mohamed.microservice.order.service.exceptions.ProductNotInStockException;
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
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public CreateOrderResponseDto addOrder(CreateOrderRequestDto data) {
        boolean isProductInStock = this.inventoryClient.isInStock(data.getSkuCode(), data.getQuantity());
        if (isProductInStock) {
            Order order = this.orderMapper.toEntity(data);
            Order savedOrder = this.orderRepository.save(order);
            // ? send the message to kafka topic
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(data.getOrderNumber(), data.getUserDetails().email());
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            return this.orderMapper.toCreateDto(savedOrder);
        } else {
            throw new ProductNotInStockException("Product with skuCode " + data.getSkuCode() + " is not in stock");
        }
    }


}
