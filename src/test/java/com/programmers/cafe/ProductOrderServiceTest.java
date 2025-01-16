package com.programmers.cafe;

import com.programmers.cafe.entity.Order;
import com.programmers.cafe.entity.Product;
import com.programmers.cafe.repository.OrderRepository;
import com.programmers.cafe.entity.ProductOrder;
import com.programmers.cafe.service.ProductOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductOrderServiceTest {
    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private OrderRepository orderRepository;

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

        order.setProductOrders(List.of(productOrder1, productOrder2));
        orderRepository.save(order);
    }

    @Test
    @DisplayName("주문 불러오기")
    void getOrderTest() {
        Order order = orderRepository.findById(1L).get();

        assertThat(order.getEmail()).isEqualTo("email@email.com");
        assertThat(order.getProductOrders()).hasSize(2);
        assertThat(order.getTotalPrice()).isEqualTo(8000);
    }

    @Test
    @DisplayName("주문 수정하기")
    void modifyOrderTest() {
        Order order = orderRepository.findById(1L).get();
        List<ProductOrder> productOrders = order.getProductOrders();
        ProductOrder productOrder1 = productOrders.get(0);
        ProductOrder productOrder2 = productOrders.get(1);

        productOrderService.modifyProductOrder(productOrder1, 2);
        productOrderService.modifyProductOrder(productOrder2, 3);

        assertThat(productOrder1.getAmount()).isEqualTo(2);
        assertThat(productOrder2.getAmount()).isEqualTo(3);
        assertThat(order.getTotalPrice()).isEqualTo(19000);
    }
}
