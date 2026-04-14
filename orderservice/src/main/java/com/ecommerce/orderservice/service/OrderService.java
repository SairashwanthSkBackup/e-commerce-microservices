package com.ecommerce.orderservice.service;

import org.springframework.stereotype.Service;
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

    public Order placeOrder(OrderRequest request) {
        try {
            String productUrl = "http://localhost:8082/ecommerce/api/products/" + request.getProductId();

            Product product = restTemplate.getForObject(productUrl, Product.class);

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
