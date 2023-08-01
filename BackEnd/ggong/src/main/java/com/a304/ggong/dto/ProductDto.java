package com.a304.ggong.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDto {
    //상품 id번호
    private Long productNo;
    //상품 pin 번호
    private String pin;
    //상품 가격정보
    private int price;
}
