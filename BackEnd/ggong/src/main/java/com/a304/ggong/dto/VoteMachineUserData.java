package com.a304.ggong.dto;

import com.a304.ggong.entity.Machine;
import com.a304.ggong.entity.Question;
import com.a304.ggong.entity.User;
import com.a304.ggong.entity.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteMachineUserData {
    //투표 결과 상세 조회 시 필요한 데이터
    private Long questionID;
    private int answer;         // 투표에서 고른 답안
    private Timestamp voteDate; //투표 시간
    private String areaGu;      //투표한 기기 지역
    private String machineName; //투표한 기기명

    @Column(nullable = true)
    private String ageRange;    //투표한 유저 연령대

    public VoteMachineUserData(Question entityQ, Vote entityV, Machine entityM, User entityU){
        this.questionID = entityQ.getQuestionID();
        this.answer = entityV.getAnswer();
        this.voteDate = entityV.getVoteDate();
        this.areaGu = entityM.getAreaGu();
        this.machineName = entityM.getName();
        this.ageRange = entityU.getAgeRange();
    }

//    public VoteMachineUserData(int answer, Timestamp voteDate, String areaGu, String machineName, String ageRange) {
//        this.answer = answer;
//        this.voteDate = voteDate;
//        this.areaGu = areaGu;
//        this.machineName = machineName;
//        this.ageRange = ageRange;
//    }
//
//    public static VoteMachineUserData fromVoteEntity(Vote entityV) {
//        return new VoteMachineUserData(
//                entityV.getAnswer(),
//                entityV.getVoteDate(),
//                entityV.getMachine().getAreaGu(),
//                entityV.getMachine().getName(),
//                entityV.getUser().getAgeRange()
//        );
//    }
}
