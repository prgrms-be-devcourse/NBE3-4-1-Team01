package com.programmers.cafe.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer amount;

    private Integer totalPrice;

    @PrePersist
    @PreUpdate
    public void calculateTotalPrice() {
        this.totalPrice = product.getPrice() * amount;
    }
}
