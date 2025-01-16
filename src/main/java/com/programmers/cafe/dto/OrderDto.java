package com.programmers.cafe.dto;

import com.programmers.cafe.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    private Long id;
    private String email;
    private LocalDateTime createdAt;
    private String address;
    private String postalCode;
    private int status;
    private List<ProductOrderDto> productOrders;
    private Integer totalPrice;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.email = order.getEmail();
        this.createdAt = order.getCreatedAt();
        this.address = order.getAddress();
        this.postalCode = order.getPostalCode();
        this.totalPrice = order.getTotalPrice();
        this.productOrders = order.getProductOrders()
                .stream()
                .map(ProductOrderDto::new)
                .collect(Collectors.toList());
    }
}
