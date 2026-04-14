package com.ecommerce.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponse {
    private Long orderId;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private Double totalPrice;
}
