package com.shan.sb.ms.orderservice.repository;

import com.shan.sb.ms.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
