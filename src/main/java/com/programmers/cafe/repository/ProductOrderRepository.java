package com.programmers.cafe.repository;

import com.programmers.cafe.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
}
