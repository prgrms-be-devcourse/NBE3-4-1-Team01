package com.programmers.cafe.service;

import com.programmers.cafe.dto.OrderDto;
import com.programmers.cafe.entity.Order;
import com.programmers.cafe.entity.Product;
import com.programmers.cafe.repository.OrderRepository;
import com.programmers.cafe.entity.ProductOrder;
import com.programmers.cafe.repository.ProductOrderRepository;
import com.programmers.cafe.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductOrderServiceTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void initData() {
        Product product1 = Product.builder()
                .name("원두1")
                .price(5000)
                .build();

        Product product2 = Product.builder()
                .name("원두2")
                .price(3000)
                .build();

        Order order = Order.builder()
                .email("email@email.com")
                .createdAt(LocalDateTime.of(2025, 1, 1, 0, 0))
                .address("주소1")
                .postalCode("12345")
                .build();

        ProductOrder productOrder1 = ProductOrder.builder()
                .product(product1)
                .order(order)
                .amount(1)
                .build();

        ProductOrder productOrder2 = ProductOrder.builder()
                .product(product2)
                .order(order)
                .amount(1)
                .build();

        productRepository.save(product1);
        productRepository.save(product2);
        productOrderRepository.save(productOrder1);
        productOrderRepository.save(productOrder2);

        order.setProductOrders(List.of(productOrder1, productOrder2));
        orderRepository.save(order);
    }

    @Test
    @DisplayName("주문 수정 - 수량과 주소 변경")
    void modifyOrderTest() {
        Order order = orderRepository.findById(1L).get();
        List<ProductOrder> productOrders = new ArrayList<>(order.getProductOrders());

        order.setAddress("주소2");
        productOrders.get(0).setAmount(3);
        order.setProductOrders(productOrders);
        orderService.modifyOrder(new OrderDto(order));

        //변경된 Order 바로 DB에 반영
        entityManager.flush();
        entityManager.clear();

        Order modifiedOrder = orderRepository.findById(1L).get();
        List<ProductOrder> modifiedProductOrders = new ArrayList<>(modifiedOrder.getProductOrders());

        assertThat(modifiedProductOrders.get(0).getAmount()).isEqualTo(3);
        assertThat(modifiedOrder.getAddress()).isEqualTo("주소2");
    }
}
