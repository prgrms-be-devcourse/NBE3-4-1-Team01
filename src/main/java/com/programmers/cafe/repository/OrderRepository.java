package com.programmers.cafe.repository;

import com.programmers.cafe.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByEmail(String email);
}
