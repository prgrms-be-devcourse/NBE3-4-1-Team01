package com.programmers.cafe.service;

import com.programmers.cafe.dto.OrderRequest;
import com.programmers.cafe.entity.Order;
import com.programmers.cafe.entity.Product;
import com.programmers.cafe.entity.ProductOrder;
import com.programmers.cafe.global.DeliveryStatus;
import com.programmers.cafe.repository.OrderRepository;
import com.programmers.cafe.repository.ProductOrderRepository;
import com.programmers.cafe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateOrderService {
    private final OrderRepository orderRepository;
    private final ProductOrderRepository productOrderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void createOrder(OrderRequest orderRequest) {
        //주문 제품 생성
        List<ProductOrder> productOrders = new ArrayList<>();
        for(int i = 0; i<orderRequest.getProductOrders().size(); i++){
            ProductOrder productOrder = new ProductOrder();
            productOrder.setAmount(orderRequest.getProductOrders().get(i).getAmount());
            Optional<Product> product = productRepository.findById(orderRequest.getProductOrders().get(i).getProductId());
            productOrder.setProduct(product.get());
            productOrders.add(productOrder);
        }

        //주문 생성
        Order order = Order.builder()
                .email(orderRequest.getEmail())
                .address(orderRequest.getAddress())
                .postalCode(orderRequest.getPostalCode())
                .createdAt(LocalDateTime.now())
                .productOrders(productOrders)
                .status(DeliveryStatus.ORDER_COMPLETED)
                .build();
        Order savedOrder = orderRepository.save(order);

        //연관관계, 주문 저장
        for(ProductOrder i : productOrders){
            i.setOrder(savedOrder);
            productOrderRepository.save(i);
        }
    }
}
