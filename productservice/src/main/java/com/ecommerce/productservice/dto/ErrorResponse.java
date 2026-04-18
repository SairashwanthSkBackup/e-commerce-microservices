package com.ecommerce.productservice.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    
    private String message;
    private Integer status;
    private LocalDateTime timestamp;
}
