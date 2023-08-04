package com.a304.ggong.service;

import com.a304.ggong.dto.response.AllUserResponse;
import com.a304.ggong.dto.response.GenderStatResponse;
import com.a304.ggong.dto.response.MachineStatResponse;
import com.a304.ggong.dto.response.TodayUserResponse;

public interface StatusService {
    //당일 수거함 사용자 수 조회
    Long selectUserCnt(TodayUserResponse response);

    //전체 회원수 조회
    Long selectOurUserCnt(AllUserResponse response);

    //사용자 통계 데이터 조회
    Long selectLastUserCnt(AllUserResponse response);

    //연령대별 통계 데이터 조회
    Long selectUserByAgeCnt(GenderStatResponse response);

    //성별 통계 데이터 조회
    Long selectUserByGenderCnt(GenderStatResponse response);

    //기기별 통계 조회
    Long selectUserByMachine(MachineStatResponse response);
}
