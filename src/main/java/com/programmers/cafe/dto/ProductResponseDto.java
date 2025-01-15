package com.programmers.cafe.dto;

import com.programmers.cafe.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String name;
    private int price;
    private String filePath;

    public static ProductResponseDto of(Product product) {
        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice(), product.getFilePath());
    }
}
