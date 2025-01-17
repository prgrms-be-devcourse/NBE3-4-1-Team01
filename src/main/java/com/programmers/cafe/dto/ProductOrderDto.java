package com.programmers.cafe.dto;

import com.programmers.cafe.entity.Product;
import com.programmers.cafe.entity.ProductOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProductOrderDto {
    private Long id;
    private String productName;
    private Integer price;
    private Integer amount;

    public ProductOrderDto(ProductOrder productOrder) {
        Product product = productOrder.getProduct();
        this.id = productOrder.getId();
        this.productName = product.getName();
        this.price = product.getPrice();
        this.amount = productOrder.getAmount();
    }
}
