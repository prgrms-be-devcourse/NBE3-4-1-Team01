package com.programmers.cafe.service;

import com.programmers.cafe.dto.ProductRequestDto;
import com.programmers.cafe.dto.ProductResponseDto;
import com.programmers.cafe.entity.Product;
import com.programmers.cafe.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 상품입니다."));

        product.update(requestDto);
        return ProductResponseDto.of(product);
    }
}
