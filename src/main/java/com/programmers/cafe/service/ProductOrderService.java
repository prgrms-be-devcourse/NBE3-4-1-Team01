package com.programmers.cafe.service;

import com.programmers.cafe.entity.Order;
import com.programmers.cafe.entity.Product;
import com.programmers.cafe.entity.ProductOrder;
import com.programmers.cafe.repository.OrderRepository;
import com.programmers.cafe.repository.ProductOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductOrderService {
    private final ProductOrderRepository productOrderRepository;
    private final OrderRepository orderRepository;

    public void modifyProductOrder(ProductOrder productOrder, Integer amount) {
        productOrder.setAmount(amount);
        productOrderRepository.save(productOrder);
    }
    public void createProductOrder(Product product, Integer amount){
        ProductOrder productOrder = new ProductOrder();
        Order order = new Order();
        orderRepository.save(order);

        productOrder.setOrder(order);
        productOrder.setProduct(product);
        productOrder.setAmount(amount);
        productOrderRepository.save(productOrder);
    }
}
