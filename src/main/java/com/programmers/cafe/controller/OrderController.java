package com.programmers.cafe.controller;

import com.programmers.cafe.dto.OrderRequestDto;
import com.programmers.cafe.entity.Order;
import com.programmers.cafe.repository.OrderRepository;
import com.programmers.cafe.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 주문 생성 API (POST)
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDto orderRequestDto) {

        Order order = orderService.createOrder(orderRequestDto);
        return new ResponseEntity<>(order, HttpStatus.CREATED);

    }
}
