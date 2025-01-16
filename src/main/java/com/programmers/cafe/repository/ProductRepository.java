package com.programmers.cafe.repository;

import com.programmers.cafe.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findById(Long id);

public interface ProductRepository extends JpaRepository<Product, Long> {
}
