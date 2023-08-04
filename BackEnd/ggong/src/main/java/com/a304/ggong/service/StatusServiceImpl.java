package com.a304.ggong.service;

import com.a304.ggong.dto.response.AllUserResponse;
import com.a304.ggong.dto.response.GenderStatResponse;
import com.a304.ggong.dto.response.MachineStatResponse;
import com.a304.ggong.dto.response.TodayUserResponse;
import com.a304.ggong.entity.User;
import com.a304.ggong.entity.Vote;
import com.a304.ggong.repository.MachineRepository;
import com.a304.ggong.repository.UserRepository;
import com.a304.ggong.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.OptionalLong;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService{

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final MachineRepository machineRepository;

    //당일 수거함 사용자 수 조회
    @Override
    public Long selectUserCnt(TodayUserResponse response) {
        return null;
    }

    //전체 회원 수 조회
    @Override
    public Long selectOurUserCnt(AllUserResponse response){
        Long ourUser = userRepository.countBy();
        return ourUser;
    }

    //사용자 통계 데이터 조회
    @Override
    public Long selectLastUserCnt(AllUserResponse response) {
        //현재 시간 입력
        LocalDate today = LocalDate.now();
        //하루 기간 설정
        LocalDate end = today.plusDays(1);
//        Vote vote = voteRepository.countByVoteDate(today, end);
        return null;
    }

    //연령대별 통계 데이터 조회
    @Override
    public String[][] selectUserByAgeCnt(GenderStatResponse response) {
        String[][] userGender = new String[2][2];
        userGender[0][0] = "남성";
        userGender[0][1] = userRepository.countUserByGender(response.getMale());
        return null;
    }

    //성별 통계 데이터 조회
    @Override
    public Long selectUserByGenderCnt(GenderStatResponse response) {
        Long userGender = userRepository.countUserByGender();
        return null;
    }

    //기기별 통계 조회
    @Override
    public Long selectUserByMachine(MachineStatResponse response) {
        return null;
    }
}
