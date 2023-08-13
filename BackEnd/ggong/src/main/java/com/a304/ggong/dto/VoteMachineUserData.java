package com.a304.ggong.dto;

import com.a304.ggong.entity.Machine;
import com.a304.ggong.entity.User;
import com.a304.ggong.entity.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteMachineUserData {
    //투표 결과 상세 조회 시 필요한 데이터

    private int answer;         // 투표에서 고른 답안
    private Timestamp voteDate; //투표 시간
    private String areaGu;      //투표한 기기 지역
    private String machineName; //투표한 기기명
    private String ageRange;    //투표한 유저 연령대

    public VoteMachineUserData(Vote entityV){
        this.answer = entityV.getAnswer();
        this.voteDate = entityV.getVoteDate();
        this.areaGu = entityV.getMachine().getAreaGu();
        this.machineName = entityV.getMachine().getName();
        this.ageRange = entityV.getUser().getAgeRange();
    }
}
