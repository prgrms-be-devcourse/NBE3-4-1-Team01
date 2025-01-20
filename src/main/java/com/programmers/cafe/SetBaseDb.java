package com.programmers.cafe;

import com.programmers.cafe.dto.ProductRequestDto;
import com.programmers.cafe.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SetBaseDb {
    private final ProductService productService;
    @Bean
    public String productSetting(){
        if(!productService.getList().isEmpty()){
            return "상품이 준비되었습니다.";
        }else {setting();}
        return "상품이 준비되었습니다.";
    }
    public void setting(){
        ProductRequestDto Columbia_Nariñó = new ProductRequestDto("Columbia_Nariñó", 5000, "/images/Columbia_Nariñó.jpeg");
        productService.create(Columbia_Nariñó);

        ProductRequestDto Brazil_Serra_Do_Caparaó = new ProductRequestDto("Brazil_Serra_Do_Caparaó", 5000, "/images/Brazil_Serra_Do_Caparaó.jpeg");
        productService.create(Brazil_Serra_Do_Caparaó);

        ProductRequestDto Colombia_Quindio = new ProductRequestDto("Colombia_Quindio", 5000, "/images/Colombia_Quindio.webp");
        productService.create(Colombia_Quindio);

        ProductRequestDto Ethiopia_Sidamo = new ProductRequestDto("Ethiopia_Sidamo", 5000, "/images/Ethiopia_Sidamo.jpg");
        productService.create(Ethiopia_Sidamo);

    }
}
