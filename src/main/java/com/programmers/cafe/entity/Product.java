package com.programmers.cafe.entity;

import com.programmers.cafe.dto.ProductRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int price;

    // TODO: 정적 파일(사진) 추가 후 경로 등록
    @Column
    private String filePath;

    public Product(String name, int price, String filePath) {
        this.name = name;
        this.price = price;
        this.filePath = filePath;
    }

    public void update (ProductRequestDto requestDto){
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.filePath = requestDto.getFilePath();
    }
}

