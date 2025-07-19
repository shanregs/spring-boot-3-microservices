package com.shan.sb.ms.orderservice.controller;

import com.shan.sb.ms.orderservice.dto.OrderRequest;
import com.shan.sb.ms.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        log.info("Creating order - {}", orderRequest);
        orderService.placeOrder(orderRequest);
        return "Order Placed Successfully";
    }
}
