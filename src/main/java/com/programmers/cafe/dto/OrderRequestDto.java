package com.programmers.cafe.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    private String email;
    private String address;
    private String postalCode;
    private List<OrderProductDto> products = new ArrayList<>(); ; // 주문에 포함된 제품 목록

    @Getter
    @Setter
    public static class OrderProductDto {
        private Long productId;
        private int amount;
    }
}
