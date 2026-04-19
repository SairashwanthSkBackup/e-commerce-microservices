package com.ecommerce.orderservice.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.orderservice.dto.OrderRequest;
import com.ecommerce.orderservice.dto.Product;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.exception.ResourceNotFoundException;
import com.ecommerce.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    public Order placeOrder(OrderRequest request, String authHeader) {
        try {
            String productUrl = "http://productservice:8082/ecommerce/api/products/" + request.getProductId();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authHeader);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<Product> response = restTemplate.exchange(productUrl, HttpMethod.GET, entity, Product.class);

            Product product = response.getBody();

            if (product == null) {
                throw new ResourceNotFoundException("Product not found");
            }

            // String userUrl = "http://localhost:8082/ecommerce/api/auth/" +
            // request.getUserId();

            Double total = product.getPrice() * request.getQuantity();

            Order order = new Order();
            order.setUserId(request.getUserId());
            order.setProductId(request.getProductId());
            order.setQuantity(request.getQuantity());
            order.setTotalPrice(total);

            return orderRepository.save(order);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new ResourceNotFoundException("Product not found");
        }
    }
}
