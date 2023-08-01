package com.a304.ggong.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    //상품 id번호
    @Id
    @GeneratedValue
    @Column(name = "product_no")
    private Long productNo;

    //상품 pin 번호
    @Column(name = "pin")
    private String pin;

    //상품 가격정보
    @Column(name = "price")
    private int price;
}
