package com.programmers.cafe.service;

import com.programmers.cafe.dto.ProductRequestDto;
import com.programmers.cafe.dto.ProductResponseDto;
import com.programmers.cafe.entity.Product;
import com.programmers.cafe.exception.DataNotFoundException;
import com.programmers.cafe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getList() {
        List<Product> productList = productRepository.findAll();
        if (productList.isEmpty()) {
            System.out.println("상품이 없습니다.");
        }
        return productList;
    }

    public Product getProduct(Long id) {
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new DataNotFoundException("product not found");
        }
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        product.update(requestDto);
        return ProductResponseDto.of(product);
    }

    public ProductResponseDto deleteProduct(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
        ProductResponseDto responseDto = ProductResponseDto.of(product);

        productRepository.delete(product);
        return responseDto;
    }
}