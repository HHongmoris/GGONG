package com.a304.ggong.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "point")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Point {
    //포인트 번호(id)
    @Id
    @GeneratedValue
    @Column(name = "point_no")
    private Long pointNo;

    //포인트 적립/사용 시간
    @Column(name = "event_time")
    private String eventTime;

    //적립/사용 포인트(적립은 +, 사용은 -)
    @Column(name = "point")
    private int point;

    //잔여 포인트
    @Column(name = "balance_point")
    private int balancePoint;

    //투표 번호(vote 테이블)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_no")
    private Vote vote;

    //사용자 번호(user 테이블)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    //구매 번호(buy 테이블)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buy_no")
    private Buy buy;
}