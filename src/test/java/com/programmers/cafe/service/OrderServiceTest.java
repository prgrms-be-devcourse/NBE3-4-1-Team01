package com.programmers.cafe.service;

import com.programmers.cafe.dto.OrderDto;
import com.programmers.cafe.dto.OrderFilterDto;
import com.programmers.cafe.entity.Order;
import com.programmers.cafe.entity.Product;
import com.programmers.cafe.entity.ProductOrder;
import com.programmers.cafe.repository.OrderRepository;
import com.programmers.cafe.repository.ProductOrderRepository;
import com.programmers.cafe.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Autowired
    private OrderFilterDto filter;

    @BeforeEach
    void initData() {
        List<ProductOrder> emptyList = new ArrayList<>();
        // 테스트 데이터: 16개
        List<Order> orders = List.of(
                Order.builder().email("example01@example.com").address("대한민국").postalCode("12345").status(0).productOrders(emptyList).build(),
                Order.builder().email("example01@example.com").address("대한민국").postalCode("00000").status(0).productOrders(emptyList).build(),
                Order.builder().email("example02@example.com").address("대한민국").postalCode("00123").status(0).productOrders(emptyList).build(),
                Order.builder().email("example03@example.com").address("대한민국").postalCode("12345").status(1).productOrders(emptyList).build(),
                Order.builder().email("example04@example.com").address("대한민국").postalCode("12345").status(0).productOrders(emptyList).build(),
                Order.builder().email("example04@example.com").address("대한민국").postalCode("00000").status(0).productOrders(emptyList).build(),
                Order.builder().email("example05@example.com").address("대한민국").postalCode("00123").status(0).productOrders(emptyList).build(),
                Order.builder().email("example04@example.com").address("대한민국").postalCode("12345").status(0).productOrders(emptyList).build(),
                Order.builder().email("example04@example.com").address("대한민국").postalCode("00000").status(0).productOrders(emptyList).build(),
                Order.builder().email("example05@example.com").address("대한민국").postalCode("00123").status(0).productOrders(emptyList).build(),
                Order.builder().email("example01@example.com").address("대한민국").postalCode("12345").status(1).productOrders(emptyList).build(),
                Order.builder().email("example02@example.com").address("대한민국").postalCode("56789").status(1).productOrders(emptyList).build(),
                Order.builder().email("example05@example.com").address("대한민국").postalCode("00123").status(0).productOrders(emptyList).build(),
                Order.builder().email("example03@example.com").address("대한민국").postalCode("12345").status(0).productOrders(emptyList).build(),
                Order.builder().email("example04@example.com").address("대한민국").postalCode("00000").status(0).productOrders(emptyList).build(),
                Order.builder().email("example05@example.com").address("대한민국").postalCode("00123").status(0).productOrders(emptyList).build()
        );

        orderRepository.saveAll(orders);
    }

    @Test
    @DisplayName("주문 전체 조회")
    void t1() {
        // given

        // when
        List<OrderDto> orders = orderService.findAll();

        // then
        assertThat(orders).isNotNull();
        assertThat(orders.size()).isEqualTo(16);
    }

    @Test
    @DisplayName("주문 필터 조회 - 배송 준비중")
    void t2() {
        // given
        filter.setDeliveryStatus(0);
        filter.setEmail("");
        int page = 1; // 2페이지

        // when
        List<OrderDto> orders = orderService.getOrderByFilters(filter, page).getContent();

        // then
        assertThat(orders).isNotNull();
        assertThat(orders.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("주문 필터 조회 - 배송중")
    void t3() {
        // given
        filter.setDeliveryStatus(1);
        filter.setEmail("");
        int page = 0;

        // when
        List<OrderDto> orders = orderService.getOrderByFilters(filter, page).getContent();

        // then
        assertThat(orders).isNotNull();
        assertThat(orders.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("주문 필터 조회 - 이메일")
    void t4() {
        // given
        filter.setDeliveryStatus(2);
        filter.setEmail("example01@example.com");
        int page = 0;

        // when
        List<OrderDto> orders = orderService.getOrderByFilters(filter, page).getContent();

        // then
        assertThat(orders).isNotNull();
        assertThat(orders.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("주문 전체 조회 - 페이징")
    void t5() {
        // given
        int page = 1; // 2페이지

        // when
        List<OrderDto> orders = orderService.findAllByPage(page).getContent();

        // then
        assertThat(orders).isNotNull();
        assertThat(orders.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("주문 필터 조회 - 배송 여부, 페이징")
    void t6() {
        // given
        int page = 0; // 1페이지
        filter.setDeliveryStatus(0);
        filter.setEmail("");

        // when
        List<OrderDto> orders = orderService.getOrderByFilters(filter, page).getContent();

        // then
        assertThat(orders).isNotNull();
        assertThat(orders.size()).isEqualTo(9);
    }

    @Test
    @DisplayName("주문 필터 조회 - 이메일, 페이징")
    void t7() {
        // given
        int page = 0; // 1페이지
        filter.setDeliveryStatus(2);
        filter.setEmail("example01@example.com");

        // when
        List<OrderDto> orders = orderService.getOrderByFilters(filter, page).getContent();

        // then
        assertThat(orders).isNotNull();
        assertThat(orders.size()).isEqualTo(2);
    }
}
