package com.programmers.cafe.repository;

import com.programmers.cafe.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByEmail(String email);
    List<Order> findByStatus(int status);
    List<Order> findByStatusAndEmail(int status, String email);
}
