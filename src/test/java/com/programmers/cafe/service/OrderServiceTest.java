package com.programmers.cafe.service;

import com.programmers.cafe.entity.Order;
import com.programmers.cafe.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void initData() {
        // 테스트 데이터
        List<Order> orders = List.of(
                Order.builder().email("example01@example.com").address("대한민국").postalCode("12345").status(0).build(),
                Order.builder().email("example01@example.com").address("대한민국").postalCode("00000").status(0).build(),
                Order.builder().email("example02@example.com").address("대한민국").postalCode("00123").status(0).build(),
                Order.builder().email("example03@example.com").address("대한민국").postalCode("12345").status(1).build(),
                Order.builder().email("example03@example.com").address("대한민국").postalCode("56789").status(1).build()
        );

        orderRepository.saveAll(orders);
    }

    @Test
    @DisplayName("주문 전체 조회")
    void t1() {
        // given
        int page = 0;

        // when
        List<Order> orders = orderService.findAllByPage(page).getContent();

        // then
        assertThat(orders).isNotNull();
        assertThat(orders.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("주문 필터 조회 - 배송 준비중")
    void t2() {
        // given
        int deliveryStatus = 0;
        String email = "";
        int page = 0;

        // when
        List<Order> orders = orderService.getOrderByFilters(deliveryStatus, email, page).getContent();

        // then
        assertThat(orders).isNotNull();
        assertThat(orders.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("주문 필터 조회 - 배송중")
    void t3() {
        // given
        int deliveryStatus = 1;
        String email = "";
        int page = 0;

        // when
        List<Order> orders = orderService.getOrderByFilters(deliveryStatus, email, page).getContent();

        // then
        assertThat(orders).isNotNull();
        assertThat(orders.size()).isEqualTo(2);
    }
}
