package com.programmers.cafe.service;

import com.programmers.cafe.entity.Order;
import com.programmers.cafe.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findByEmail(String email) {
        return orderRepository.findByEmail(email);
    }
}
