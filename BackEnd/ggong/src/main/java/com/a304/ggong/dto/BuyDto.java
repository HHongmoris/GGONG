package com.a304.ggong.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BuyDto {
    //구매내역 번호(id)
    private int buyNo;
    //상품 구매시간
    private String buyTime;
    //구매한 사용자 번호(user 테이블)
    private int userNo;
    //구매한 상품 번호(product 테이블)
    private int productNo;
}
