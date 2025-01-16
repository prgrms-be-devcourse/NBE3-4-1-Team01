package com.programmers.cafe.repository;

import com.programmers.cafe.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByEmail(String email);
    List<Order> findByStatus(int status);
    List<Order> findByStatusAndEmail(int status, String email);
}
