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
        return new ResponseEntity<>(order, HttpStatus.CREATED); // 성공 시 201 Created 반환

    }

//    // GET 요청에 대한 처리 (405 Method Not Allowed 응답)
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<String> handleGetRequest() {
//        return new ResponseEntity<>("GET method is not supported for /order", HttpStatus.METHOD_NOT_ALLOWED);
//    }
//
//    // POST 외 다른 요청 메소드 처리 (405 Method Not Allowed 응답)
//    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
//    public ResponseEntity<String> handleOtherMethods() {
//        return new ResponseEntity<>("This method is not supported for /order", HttpStatus.METHOD_NOT_ALLOWED);
//    }

//    // HttpRequestMethodNotSupportedException을 처리하는 예외 핸들러
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public ResponseEntity<String> handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
//        // 특정 메소드가 지원되지 않을 때 더 상세한 메시지를 반환
//        return new ResponseEntity<>("Method Not Supported: " + e.getMethod(), HttpStatus.METHOD_NOT_ALLOWED);
//    }
}
