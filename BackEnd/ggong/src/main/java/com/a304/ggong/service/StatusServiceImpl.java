package com.a304.ggong.service;

import com.a304.ggong.dto.response.*;
import com.a304.ggong.entity.User;
import com.a304.ggong.entity.Vote;
import com.a304.ggong.repository.MachineRepository;
import com.a304.ggong.repository.UserRepository;
import com.a304.ggong.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
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
    public List<GenderStatResponse> selectUserByGenderCnt() {
        List<GenderStatResponse> genderStats = new ArrayList<>();
        List<String> genders = Arrays.asList("남성", "여성");

        for(String gender : genders){
            Long genderCnt = userRepository.countUserByGender(gender);

            if(genderCnt != null){
                GenderStatResponse genderStatResponse = new GenderStatResponse(gender, genderCnt);
                genderStats.add(genderStatResponse);
            }
        }
        return genderStats;
    }

    //기기별 통계 조회
    @Override
    public List<MachineStatResponse> selectUserByMachine() {
        List<MachineStatResponse> machineUserStats = new ArrayList<>();
        //기기명 전부 불러오기
        List<String> machines = machineRepository.findNameBy();
        //현재 날짜 설정
        LocalDateTime now = LocalDateTime.now();
        //지난주 날짜 설정
        LocalDateTime startOfLastWeek = now.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).minusDays(6);
        LocalDateTime endOfLastWeek = now.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
        //기기명에 따른 사용자 수 입력
        for(String machine : machines){
            //기기별 지난 사용자 수 계산
            Long machineUserCnt = voteRepository.countByMachine(machine, startOfLastWeek, endOfLastWeek);
            //response에 기기명, 사용자 수 담기
            MachineStatResponse machineStatResponse = new MachineStatResponse(machine, machineUserCnt);
            machineUserStats.add(machineStatResponse);
            
        }
        return machineUserStats;
    }
}
