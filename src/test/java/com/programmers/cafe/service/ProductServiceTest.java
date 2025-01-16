package com.programmers.cafe.service;

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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

}