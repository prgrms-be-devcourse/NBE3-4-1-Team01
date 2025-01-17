package com.programmers.cafe.service;

import com.programmers.cafe.dto.OrderDto;
import com.programmers.cafe.dto.ProductOrderDto;
import com.programmers.cafe.entity.Order;
import com.programmers.cafe.entity.Product;
import com.programmers.cafe.repository.OrderRepository;
import com.programmers.cafe.repository.ProductOrderRepository;
import com.programmers.cafe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductOrderRepository productOrderRepository;
    private final ProductRepository productRepository;

    public List<OrderDto> findAll() {
        return orderRepository
                .findAll()
                .stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    public void deleteById(long id) {
        orderRepository.deleteById(id);
    }

    public List<OrderDto> getOrderByFilters(int deliveryStatus, String email) {
        List<Order> orders;

        if (deliveryStatus == 2 && (email == null || email.isEmpty())) {
            return null;
        } else if (deliveryStatus == 2) {
            // "모두" 선택 및 이메일 필터만 적용
            orders = orderRepository.findByEmail(email);
        } else if (email == null || email.isEmpty()) {
            // 배송 상태 필터만 적용
            orders = orderRepository.findByStatus(deliveryStatus);
        } else {
            // 배송 상태와 이메일 모두 필터 적용
            orders = orderRepository.findByStatusAndEmail(deliveryStatus, email);
        }

        return orders.stream().map(OrderDto::new).collect(Collectors.toList());
    }

    public OrderDto findById(long id) {
        Order order = orderRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("주문 항목을 찾을 수 없습니다."));

        return new OrderDto(order);
    }

    @Transactional
    public void modifyOrder(OrderDto orderDto) {
        List<Product> products = productRepository.findAll();
        Order order = orderDto.toOrder(products);
        orderRepository.save(order);
    }
}
