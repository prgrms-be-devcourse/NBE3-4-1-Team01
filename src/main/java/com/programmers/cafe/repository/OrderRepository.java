package com.programmers.cafe.repository;

import com.programmers.cafe.entity.Order;
import com.programmers.cafe.global.DeliveryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByEmail(String email, Pageable pageable);
    Page<Order> findByStatus(DeliveryStatus status, Pageable pageable);
    Page<Order> findByStatusAndEmail(DeliveryStatus status, String email, Pageable pageable);
}
