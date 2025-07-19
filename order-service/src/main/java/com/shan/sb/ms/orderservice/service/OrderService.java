package com.shan.sb.ms.orderservice.service;

import com.shan.sb.ms.orderservice.client.InventoryClient;
import com.shan.sb.ms.orderservice.dto.OrderRequest;
import com.shan.sb.ms.orderservice.model.Order;
import com.shan.sb.ms.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    public void placeOrder(OrderRequest orderRequest) {
        var stockAvailable = inventoryClient.inStock(orderRequest.skuCode(), orderRequest.quantity());
        if(!stockAvailable) {
            throw new RuntimeException("Product with skucode "+ orderRequest.skuCode() + " is not in stock");
        }

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setSkuCode(orderRequest.skuCode());
        order.setQuantity(orderRequest.quantity());
        orderRepository.save(order);
    }

}
