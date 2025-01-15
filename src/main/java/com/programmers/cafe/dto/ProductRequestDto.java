package com.programmers.cafe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductRequestDto {
    @NotNull @NotBlank
    private String name;

    @NotNull @PositiveOrZero
    private int price;

    // TODO: 정적 파일(사진) 등록
     private String filePath;

    public ProductRequestDto(String name, int price, String filePath) {
        this.name = name;
        this.price = price;
        this.filePath = filePath;
    }
}
