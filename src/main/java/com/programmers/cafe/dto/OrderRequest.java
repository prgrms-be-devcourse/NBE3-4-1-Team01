package com.programmers.cafe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String address;
    @NotNull
    private String postalCode;
    @NotNull
    private List<ProductOrderRequest> productOrders;
}