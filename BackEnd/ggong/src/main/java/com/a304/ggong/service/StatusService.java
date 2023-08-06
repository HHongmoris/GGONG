package com.a304.ggong.service;

import com.a304.ggong.dto.response.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

public interface StatusService {
    //당일 수거함 사용자 수 조회
    Long selectUserCnt(TodayUserResponse response);

    //전체 회원수 조회
    Long selectOurUserCnt(AllUserResponse response);

    //사용자 통계 데이터 조회
    Long selectLastUserCnt(AllUserResponse response);

    //연령대별 통계 데이터 조회
    List<AgeStatResponse> selectUserByAgeCnt();

    //성별 통계 데이터 조회
    List<GenderStatResponse> selectUserByGenderCnt();

    //기기별 통계 조회
    List<MachineStatResponse> selectUserByMachine();
}
