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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void start(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("성공 테스트")
    void 전체_상품_조회1() {
        //given
        Product product1 = new Product("브라질 원두", 5000, "qwe.jpg");
        Product product2 = new Product("에티오피아 원두", 6000, "asd.jpg");
        List<Product> fakeList = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(fakeList);

        //when
        List<Product> testList = productService.getList();

        //then
        assertEquals(2,testList.size());
        assertThat(testList.get(0).getName()).isEqualTo(fakeList.get(0).getName());
        assertThat(testList.get(1).getName()).isEqualTo(fakeList.get(1).getName());
        assertThat(testList.get(0).getPrice()).isEqualTo(fakeList.get(0).getPrice());
        assertThat(testList.get(1).getPrice()).isEqualTo(fakeList.get(1).getPrice());
    }
    @Test
    @DisplayName("성공 테스트")
    void 개별_상품_조회(){
        //given
        Product fakeProduct = new Product("브라질 원두", 5000, "qwe.jpg");
        when(productRepository.findById(1L)).thenReturn(Optional.of(fakeProduct));

        //when
        Product testproduct = productService.getProduct(1L);

        //then
        assertThat(testproduct).isEqualTo(fakeProduct);
    }
    @Test
    @DisplayName("예외 테스트")
    void 개별_상품_조회_예외상황(){
        //given
        Product fakeProduct = new Product("브라질 원두", 5000, "qwe.jpg");
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        //when

        //then
        assertThrows(DataNotFoundException.class, () -> {productService.getProduct(1L); });
    }

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

    @Test
    @DisplayName("id에 해당하는 상품이 존재하지 않는 경우 실패")
    public void update_product_not_exist() {
        // given
        ProductRequestDto requestDto = new ProductRequestDto("new_name", 20000, "path/name.png");

        Long productId = product.getId();
        productRepository.deleteById(product.getId());

        assertThrows(IllegalArgumentException.class,
            () -> productService.updateProduct(productId, requestDto));
    }
}