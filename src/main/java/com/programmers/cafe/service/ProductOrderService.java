package com.programmers.cafe.service;

import com.programmers.cafe.entity.ProductOrder;
import com.programmers.cafe.repository.ProductOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductOrderService {
    private final ProductOrderRepository productOrderRepository;

    public void modifyProductOrder(ProductOrder productOrder, Integer amount) {
        productOrder.setAmount(amount);
        productOrderRepository.save(productOrder);
    }
}
