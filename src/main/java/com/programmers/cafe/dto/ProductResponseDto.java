package com.programmers.cafe.dto;

import com.programmers.cafe.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductResponseDto {
    private Long id;
    private String name;
    private int price;
    private String filePath;

    public static ProductResponseDto of(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setName(product.getName());
        responseDto.setPrice(product.getPrice());
        responseDto.setFilePath(product.getFilePath());

        return responseDto;
    }
}
