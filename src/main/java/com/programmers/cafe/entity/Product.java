package com.programmers.cafe.entity;

전체상품조회
import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
main
import lombok.Getter;

@Entity
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int price;

    // TODO: 정적 파일(사진) 추가 후 경로 등록
    @Column
    private String filePath

    public Product(String name, int price, String filePath) {
        this.name = name;
        this.price = price;
        this.filePath = filePath;
    }

    public Product() {
    }
}

}

