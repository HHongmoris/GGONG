package com.a304.ggong.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "point")
@Getter
@Setter
public class Point {
    @Id
    @GeneratedValue
    @Column(name = "point_no")
    //포인트 번호(id)
    private int pointNo;

    @Column(name = "event_time")
    //포인트 적립/사용 시간
    private String eventTime;

    @Column(name = "point")
    //적립/사용 포인트(적립은 +, 사용은 -)
    private int point;

    @Column(name = "balance_point")
    //잔여 포인트
    private int balancePoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_no")
    //투표 번호(vote 테이블)
    private Vote voteNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    //사용자 번호(user 테이블)
    private User userNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buy_no")
    //구매 번호(buy 테이블)
    private Buy buyNo;
}
