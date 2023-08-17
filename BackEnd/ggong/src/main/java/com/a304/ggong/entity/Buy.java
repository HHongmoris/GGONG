package com.a304.ggong.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "buy")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Buy {
    //구매내역 번호(id)
    @Id
    @GeneratedValue
    @Column(name = "buy_no")
    private Long buyNo;

    //상품 구매시간
    @Column(name = "buy_time")
    private Timestamp buyTime;

    //구매한 사용자 번호(user 테이블)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    //구매한 상품 번호(product 테이블)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_no")
    private Product product;

    @Builder
    public Buy(Timestamp buyTime, User user, Product product) {
        this.buyTime = buyTime;
        this.user = user;
        this.product = product;
    }
}
