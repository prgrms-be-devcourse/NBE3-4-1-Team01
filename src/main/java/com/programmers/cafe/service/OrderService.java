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

    public List<Order> findByEmail(String email) {
        if (email.isBlank())
            return this.findAll();
        else
            return orderRepository.findByEmail(email);
    }

    public void deleteById(long id) {
        orderRepository.deleteById(id);
    }
}
