package com.a304.ggong.service;

import com.a304.ggong.dto.response.AllUserResponse;
import com.a304.ggong.dto.response.GenderStatResponse;
import com.a304.ggong.dto.response.MachineStatResponse;
import com.a304.ggong.dto.response.TodayUserResponse;
import com.a304.ggong.entity.User;
import com.a304.ggong.repository.MachineRepository;
import com.a304.ggong.repository.UserRepository;
import com.a304.ggong.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService{

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final MachineRepository machineRepository;

    //당일 수거함 사용자 수 조회
    @Override
    public Long selectUserCnt(TodayUserResponse response) {
        Long todayUser = userRepository.countUserByUserNo(response.getTodayUserCount());
        return todayUser;
    }

    //사용자 통계 데이터 조회
    @Override
    public Long selectLastUserCnt(AllUserResponse response) {
        return null;
    }

    //연령대별 통계 데이터 조회
    @Override
    public Long selectUserByAgeCnt(GenderStatResponse response) {
        return null;
    }

    //성별 통계 데이터 조회
    @Override
    public Long selectUserByGenderCnt(GenderStatResponse response) {
        return null;
    }

    //기기별 통계 조회
    @Override
    public Long selectUserByMachine(MachineStatResponse response) {
        return null;
    }
}
