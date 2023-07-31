package com.a304.ggong.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "product_no")
    //상품 id번호
    private int productNo;

    @Column(name = "pin")
    //상품 pin 번호
    private String pin;

    @Column(name = "price")
    //상품 가격정보
    private int price;
}
