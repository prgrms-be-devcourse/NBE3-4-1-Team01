package com.programmers.cafe.repository;

import com.programmers.cafe.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
}
