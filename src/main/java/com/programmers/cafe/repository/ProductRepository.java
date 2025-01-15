package com.programmers.cafe.repository;

import com.programmers.cafe.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
