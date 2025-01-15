package com.programmers.cafe.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.programmers.cafe.dto.ProductRequestDto;
import com.programmers.cafe.dto.ProductResponseDto;
import com.programmers.cafe.entity.Product;
import com.programmers.cafe.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product("name", 10000, "path/name.png");
        productRepository.save(product);
    }

    @Test
    @DisplayName("상품 수정 성공")
    public void update_product() {
        // given
        ProductRequestDto requestDto = new ProductRequestDto("new_name", 20000, "path/name.png");

        // when
        ProductResponseDto responseDto = productService.updateProduct(product.getId(), requestDto);

        // then
        assertThat(responseDto).isNotNull();
        assertThat(responseDto.getId()).isEqualTo(product.getId());
        assertThat(responseDto.getName()).isEqualTo("new_name");
        assertThat(responseDto.getPrice()).isEqualTo(20000);

        Product savedProduct = productRepository.findById(product.getId()).orElseThrow();
        assertThat(savedProduct.getId()).isEqualTo(product.getId());
        assertThat(savedProduct.getName()).isEqualTo("new_name");
        assertThat(savedProduct.getPrice()).isEqualTo(20000);
    }
}