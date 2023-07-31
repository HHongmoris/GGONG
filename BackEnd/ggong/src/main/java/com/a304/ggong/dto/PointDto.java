package com.a304.ggong.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PointDto {
    //포인트 번호(id)
    private int pointNo;
    //포인트 적립/사용 시간
    private String eventTime;
    //적립/사용 포인트(적립은 +, 사용은 -)
    private int point;
    //잔여 포인트
    private int balancePoint;
    //투표 번호(vote 테이블)
    private int voteNo;
    //사용자 번호(user 테이블)
    private int userNo;
    //구매 번호(buy 테이블)
    private int buyNo;
}
