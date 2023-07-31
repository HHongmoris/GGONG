package com.a304.ggong.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "buy")
@Getter
@Setter
public class Buy {
    @Id
    @GeneratedValue
    @Column(name = "buy_no")
    //구매내역 번호(id)
    private int buyNo;

    @Column(name = "buy_time")
    //상품 구매시간
    private String buyTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    //구매한 사용자 번호(user 테이블)
    private User userNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_no")
    //구매한 상품 번호(product 테이블)
    private Product productNo;
}
