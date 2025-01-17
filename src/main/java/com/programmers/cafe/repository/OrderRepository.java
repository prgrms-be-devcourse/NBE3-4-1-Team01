package com.programmers.cafe.repository;

import com.programmers.cafe.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByEmail(String email, Pageable pageable);
    Page<Order> findByStatus(int status, Pageable pageable);
    Page<Order> findByStatusAndEmail(int status, String email, Pageable pageable);
    Page<Order> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
