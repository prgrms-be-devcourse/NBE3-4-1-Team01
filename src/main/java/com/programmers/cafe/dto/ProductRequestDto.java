package com.programmers.cafe.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProductRequestDto {
    @NotNull
    private String name;

    @NotNull
    private int price;

    // TODO: 정적 파일(사진) 등록
    // private String filePath;
}
