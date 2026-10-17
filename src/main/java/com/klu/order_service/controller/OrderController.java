
package com.klu.order_service.controller;

import com.klu.order_service.dto.OrderRequest;
import jakarta.validation.Valid;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> createOrder(
            @Valid @RequestBody OrderRequest order) {

        return Map.of(
            "message", "Order created successfully",
            "customerName", order.getCustomerName(),
            "productName", order.getProductName(),
            "quantity", order.getQuantity(),
            "price", order.getPrice()
        );
    }
}
