package com.programmers.cafe.controller;

import com.programmers.cafe.dto.OrderRequest;
import com.programmers.cafe.service.CreateOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class OrderRestController {

    private final CreateOrderService createOrderService;

    // 주문 생성 API (POST)
    @PostMapping("/create/order")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        createOrderService.createOrder(orderRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(UriComponentsBuilder.fromPath("/").build().toUri());

        return new ResponseEntity<>("Order created successfully", headers, HttpStatus.FOUND);
    }

}
