package com.programmers.cafe.service;

import com.programmers.cafe.dto.ProductRequestDto;
import com.programmers.cafe.dto.ProductResponseDto;
import com.programmers.cafe.entity.Product;
import com.programmers.cafe.exception.DataNotFoundException;
import com.programmers.cafe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<Product> getList(int page) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<Product> productPage = productRepository.findAll(pageable);

        if (productPage != null && productPage.getContent() != null) {
            productPage.getContent().forEach(product -> {
                if (product != null) {
                    System.out.println("Product ID: " + product.getId());
                } else {
                    System.out.println("Product is null");
                }
            });
        } else {
            System.out.println("Product page or content is null");
        }

        return productPage;
    }

    @Transactional(readOnly = true)
    public List<Product> getList() {
        List<Product> products = productRepository.findAll();

        if (products != null) {
            products.forEach(product -> {
                if (product != null) {
                    System.out.println("Product ID: " + product.getId());
                } else {
                    System.out.println("Product is null");
                }
            });
        } else {
            System.out.println("Product list is null");
        }

        return products;
    }

    @Transactional(readOnly = true)
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

    public void create(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setFilePath(productRequestDto.getFilePath());

        productRepository.save(product);
    }
}
