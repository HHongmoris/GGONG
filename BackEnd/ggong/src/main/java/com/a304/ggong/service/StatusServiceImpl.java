package com.a304.ggong.service;

import com.a304.ggong.dto.response.*;
import com.a304.ggong.entity.User;
import com.a304.ggong.entity.Vote;
import com.a304.ggong.repository.MachineRepository;
import com.a304.ggong.repository.UserRepository;
import com.a304.ggong.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    public TodayUserResponse selectUserCnt() {
        //현재 시간 입력
        LocalDateTime now = LocalDateTime.now();
        //하루 기간 설정
        LocalDateTime startOfToday = now.with(LocalTime.MIN);
        LocalDateTime endOfToday = now.with(LocalTime.MAX);
        //Timestamp 형식으로 변환
        Timestamp start = Timestamp.valueOf(startOfToday);
        Timestamp end = Timestamp.valueOf(endOfToday);
        
        //당일 수거함 사용자 수 조회
        Long todayUserCnt = voteRepository.countByVoteDate(start, end);
        TodayUserResponse todayUserResponse = new TodayUserResponse(todayUserCnt);
        
        return todayUserResponse;
    }

    //사용자 통계 데이터 조회
    //여기에 총 회원수, 지난달 사용자 수 들어감
    @Override
    public AllUserResponse selectAllUserCnt() {
        //현재 시간 입력
        LocalDateTime now = LocalDateTime.now();
        // 지난달의 시작일
        LocalDateTime startOfLastMonth = now.minusMonths(1).withDayOfMonth(1).with(LocalTime.MIN);
        // 지난달의 마지막 일자 (이번 달의 1일 이전)
        LocalDateTime endOfLastMonth = now.withDayOfMonth(1).minusDays(1).with(LocalTime.MAX);
        //Timestamp 형식으로 변환
        Timestamp start = Timestamp.valueOf(startOfLastMonth);
        Timestamp end = Timestamp.valueOf(endOfLastMonth);

        Long ourUserCnt = userRepository.countBy();
        Long lastUserCnt = voteRepository.countByVoteDate(start, end);
        Double lastUserCntAvg = ((double) Math.round(lastUserCnt.doubleValue()/3))/10;
        AllUserResponse allUserResponse = new AllUserResponse(ourUserCnt, lastUserCnt, lastUserCntAvg);
        
        return allUserResponse;
    }

    //연령대별 통계 데이터 조회
    @Override
    public List<AgeStatResponse> selectUserByAgeCnt() {
        //AgeStatResponse 리스트 만들기
        List<AgeStatResponse> ageStats = new ArrayList<>();
        //리스트에 연령대 입력
        List<String> ageRanges = Arrays.asList("20대", "30대", "40대", "50대", "60대 이상"); // 원하는 연령대를 추가

        //연령대별 사용자 수 입력
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
        //GenderStatResponse 리스트 만들기
        List<GenderStatResponse> genderStats = new ArrayList<>();
        //리스트에 성별 값 입력
        List<String> genders = Arrays.asList("남성", "여성");
        
        //성별에 따른 사용자 수 입력
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
        List<String> tmp = machineRepository.findNameBy();
        List<String> machines = new ArrayList<>();

        for(int idx = 1; idx < tmp.size(); idx++){
            machines.add(tmp.get(idx));
        }
        //현재 날짜 설정
        LocalDateTime now = LocalDateTime.now();

        //지난주 날짜 설정
        LocalDateTime startOfLastWeek = now.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).minusDays(6).with(LocalTime.MIN);
        LocalDateTime endOfLastWeek = now.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).with(LocalTime.MAX);
        //Timestamp로 변환
        Timestamp startDate = Timestamp.valueOf(startOfLastWeek);
        Timestamp endDate = Timestamp.valueOf(endOfLastWeek);

        //기기명에 따른 사용자 수 입력
        for(String machine : machines){
            //기기별 지난 사용자 수 계산
            Long machineUserCnt = voteRepository.countByMachine(machine, startDate, endDate);
            //response에 기기명, 사용자 수 담기
            MachineStatResponse machineStatResponse = new MachineStatResponse(machine, machineUserCnt);
            machineUserStats.add(machineStatResponse);
            
        }
        return machineUserStats;
    }
}
