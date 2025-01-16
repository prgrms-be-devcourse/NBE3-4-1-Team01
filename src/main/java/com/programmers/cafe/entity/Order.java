package com.programmers.cafe.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`order`")
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String email;

    @CreatedDate
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(length = 10)
    private String postalCode;

    private int status; // 0: 주문완료, 1: 배송중

    @OneToMany(mappedBy = "order", cascade = {CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<ProductOrder> productOrders;

    private Integer totalPrice;

    @PrePersist
    @PreUpdate
    public void calculateTotalPrice() {
        if (productOrders != null) {
            this.totalPrice = productOrders
                    .stream()
                    .mapToInt(productOrder -> productOrder.getProduct().getPrice() * productOrder.getAmount())
                    .sum();
        }
    }
}
