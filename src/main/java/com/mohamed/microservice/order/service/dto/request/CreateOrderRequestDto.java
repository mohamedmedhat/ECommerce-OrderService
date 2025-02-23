package com.mohamed.microservice.order.service.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateOrderRequestDto {
    private String orderNumber;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
