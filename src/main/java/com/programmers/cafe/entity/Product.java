package com.programmers.cafe.entity;

import com.programmers.cafe.dto.ProductRequestDto;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int price;

    @Column
    private String filePath;

    @Column
    private Boolean removeFlag;

    public Product(String name, int price, String filePath) {
        this.name = name;
        this.price = price;
        this.filePath = filePath;
        this.removeFlag = false;
    }

    public void update (ProductRequestDto requestDto){
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.filePath = requestDto.getFilePath();
    }

    public void remove() {
        this.removeFlag = true;
    }
}
