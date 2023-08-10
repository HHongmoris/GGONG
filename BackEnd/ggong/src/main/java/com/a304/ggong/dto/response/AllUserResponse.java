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

    //이거 vote 테이블에서 오늘 날짜 사람들 카운트 해서 값 넘겨줘야함
    //일단은 이렇게 써놓고 수정할 예정
//    public AllUserResponse(Vote entity){
//        this.totalUser = entity.getVoteNo();
//        this.lastMonthUser = entity.getVoteNo();
//    }
}
