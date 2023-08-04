package com.a304.ggong.service;

import com.a304.ggong.dto.response.*;
import com.a304.ggong.entity.User;
import com.a304.ggong.entity.Vote;
import com.a304.ggong.repository.MachineRepository;
import com.a304.ggong.repository.UserRepository;
import com.a304.ggong.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
    public List<AgeStatResponse> selectUserByAgeCnt() {
        List<AgeStatResponse> ageStats = new ArrayList<>();
        List<String> ageRanges = Arrays.asList("20대", "30대", "40대", "50대", "60대 이상"); // 원하는 연령대를 추가

        for (String ageRange : ageRanges) {
            Long ageRangeCnt = userRepository.countUserByAgeRange(ageRange);

            if (ageRangeCnt != null) {
                AgeStatResponse ageStatResponse = new AgeStatResponse(ageRange, ageRangeCnt);
                ageStats.add(ageStatResponse);
            }
        }
        return ageStats;
    }


    //성별 통계 데이터 조회
    @Override
    public Optional<GenderStatResponse> selectUserByGenderCnt(String gender) {
//        Long userGender = userRepository.countUserByGender();
        return null;
    }

    //기기별 통계 조회
    @Override
    public Long selectUserByMachine(MachineStatResponse response) {
        return null;
    }
}
