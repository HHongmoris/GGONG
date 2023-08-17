package com.a304.ggong.dto.response;

import com.a304.ggong.entity.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllUserResponse {
    private Long totalUser; //총 회원 수
    private Long lastMonthUser; //지난달 사용자 수
    private Double lastMonthUserAvg; //지난달 평균 사용자 수
}
