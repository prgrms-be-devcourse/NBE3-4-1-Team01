package com.programmers.cafe.service;

import com.programmers.cafe.entity.Order;
import com.programmers.cafe.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Page<Order> findAllByPage(int page) {
        return orderRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(page, 10));
    }

    public void deleteById(long id) {
        orderRepository.deleteById(id);
    }

    public Page<Order> getOrderByFilters(int deliveryStatus, String email, int page) {
        Page<Order> orders;
        Pageable pageable = PageRequest.of(page, 10);

        if (deliveryStatus == 2 && (email == null || email.isEmpty())) {
            return null;
        } else if (deliveryStatus == 2) {
            // "모두" 선택 및 이메일 필터만 적용
            orders = orderRepository.findByEmail(email, pageable);
        } else if (email == null || email.isEmpty()) {
            // 배송 상태 필터만 적용
            orders = orderRepository.findByStatus(deliveryStatus, pageable);
        } else {
            // 배송 상태와 이메일 모두 필터 적용
            orders = orderRepository.findByStatusAndEmail(deliveryStatus, email, pageable);
        }

        return orders;
    }

    public Order findById(long id) {
        return orderRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("주문 항목을 찾을 수 없습니다."));
    }

    public void modifyOrders(Order order) {
        orderRepository.save(order);
    }
}
