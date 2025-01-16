package com.programmers.cafe;

import com.programmers.cafe.entity.Product;
import com.programmers.cafe.repository.ProductRepository;
import com.programmers.cafe.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SetBaseDb {
    private final ProductService productService;
    private final ProductRepository productRepository;
    @Bean
    public String productSetting(){
        if(!productService.getList().isEmpty()){
            return "상품이 준비되었습니다.";
        }else {setting();}
        return "상품이 준비되었습니다.";
    }
    public void setting(){
        Product Columbia_Nariñó = new Product("Columbia_Nariñó", 5000, "ex1.jpg");
        productRepository.save(Columbia_Nariñó);
        Product Brazil_Serra_Do_Caparaó = new Product("Brazil_Serra_Do_Caparaó", 5000, "ex2.jpg");
        productRepository.save(Brazil_Serra_Do_Caparaó);
        Product Colombia_Quindio = new Product("Colombia_Quindio", 5000, "ex3.jpg");
        productRepository.save(Colombia_Quindio);
        Product Ethiopia_Sidamo = new Product("Ethiopia_Sidamo", 5000, "ex4.jpg");
        productRepository.save(Ethiopia_Sidamo);
    }
}
