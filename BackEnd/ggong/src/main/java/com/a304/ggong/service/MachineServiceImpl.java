package com.a304.ggong.service;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.a304.ggong.entity.QuestionType;
import com.a304.ggong.global.resource.QuestionGroup;

import org.springframework.stereotype.Service;

import com.a304.ggong.dto.response.AllMachinesResponse;
import com.a304.ggong.dto.response.LikeResponse;
import com.a304.ggong.dto.response.MachineDetailResponse;
import com.a304.ggong.entity.FavoriteMachine;
import com.a304.ggong.entity.Machine;
import com.a304.ggong.entity.Question;
import com.a304.ggong.entity.User;
import com.a304.ggong.repository.FavoriteMachineRepository;
import com.a304.ggong.repository.MachineRepository;
import com.a304.ggong.repository.QuestionRepository;
import com.a304.ggong.repository.UserRepository;
import com.a304.ggong.repository.VoteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.a304.ggong.entity.QuestionType.*;

@Slf4j
@RequiredArgsConstructor // 생성자 주입
@Service
public class MachineServiceImpl implements MachineService {

	// repo
	private final MachineRepository machineRepository;
	private final FavoriteMachineRepository favoriteMachineRepository;
	private final VoteRepository voteRepository;
	private final QuestionRepository questionRepository;
	private final UserRepository userRepository;

	// 모든 기기 조회
	@Override
	public List<AllMachinesResponse> selectAllMachines() {
		List<AllMachinesResponse> list = machineRepository.findAll().stream().map(AllMachinesResponse::new).collect(
			Collectors.toList());
		return list;
	}

	// 회원 이메일에 따라 관심 기기 리스트 조회
	@Override
	public List<LikeResponse> selectAllFavoriteMachines(String email) {

			// 먼저, Email을 이용해 유저 객체 가져와야함.
			User user = userRepository.findByEmail(email).get();

			// List<FavoriteMachine> favoriteMachine = favoriteMachineRepository.findByUser_UserNo(user.getUserNo());
			List<FavoriteMachine> favoriteMachine = favoriteMachineRepository.findByUser_UserNo(user.getUserNo());

			if(favoriteMachine.size()==0){
				return null;
			}

			List<LikeResponse> result = new ArrayList<>();

			for(int idx = 0; idx < favoriteMachine.size(); idx++){
				result.add(new LikeResponse(favoriteMachine.get(idx)));
			}

			return result; // return문을 여기에 쓰는 게 맞나..?

	}

	// 이번주에 기기에 출력될 대학 질문 리스트 구하는 메소드
	public List<Question> getUniQuestionIdList(){

		List<Question> tmpList = new ArrayList<>();

		QuestionGroup questionGroup = new QuestionGroup();

		int groupNum = questionGroup.getThisWeekGroupNum();

		List<Question> tmpCommon = questionRepository.findByGroupAndType(groupNum, 공통);
		List<Question> tmpUni = questionRepository.findByGroupAndType(groupNum, 대학);

		// 공통 넣기
		for(int idx = 0; idx < tmpCommon.size(); idx++){
			tmpList.add(tmpCommon.get(idx));
		}

		// 대학 넣기
		for(int idx = 0; idx < tmpUni.size(); idx++){
			tmpList.add(tmpUni.get(idx));
		}

		return tmpList;
	}

	// 이번주에 기기에 출력될 기업 질문 리스트 구하는 메소드
	public List<Question> getComQuestionIdList(){
		List<Question> tmpList = new ArrayList<>();

		QuestionGroup questionGroup = new QuestionGroup();

		int groupNum = questionGroup.getThisWeekGroupNum();

		List<Question> tmpCommon = questionRepository.findByGroupAndType(groupNum, 공통);
		List<Question> tmpCom = questionRepository.findByGroupAndType(groupNum, 기업);

		// 공통 넣기
		for(int idx = 0; idx < tmpCommon.size(); idx++){
			tmpList.add(tmpCommon.get(idx));
		}

		// 대학 넣기
		for(int idx = 0; idx < tmpCom.size(); idx++){
			tmpList.add(tmpCom.get(idx));
		}

		return tmpList;
	}

	// 현재 출력되고 있는 questionId 구하는 메소드
	public Long getPresentQuestionId(List<Question> list, LocalDateTime now){

		Long questionId = 0L;

		// 현재의 요일 구하기
		DayOfWeek dayOfWeek = now.getDayOfWeek();
		String stringDay = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN);

		int intDay = now.getHour();
		int idxDay = 0;

		switch(stringDay){
			case "화요일":
				intDay += (24*1);
				break;
			case "수요일":
				intDay += (24*2);
				break;
			case "목요일":
				intDay += (24*3);
				break;
			case "금요일":
				intDay += (24*4);
				break;
			case "토요일":
				intDay += (24*5);
				break;
			case "일요일":
				intDay += (24*6);
				break;
		}

		idxDay = intDay % 21;

		questionId = list.get(idxDay).getQuestionID();

		// 현재
		return questionId;
	}

	// 기기 상세 정보
	@Override
	public MachineDetailResponse selectMachineDetail(Long machineNo) {
		// MachineDetailResponse 생성자에는 machine, question 객체가 필요해
		// question 객체를 받으려면 vote객체를 타야해

		// 먼저 machineID로 machine 객체를 찾아
		Machine tmpMachine = machineRepository.findById(machineNo).get();

		// ID 구하기 전에 machine이 대학교인지 기업인지 찾아
		// 그거에 따라 질문 list 다르게 넣어줘

		List<Question> questionList = new ArrayList<>();

		String machineType = tmpMachine.getName();

		if(machineType.contains("대학교")){ // 대학교이면
			questionList = this.getUniQuestionIdList();
		}else { // 기업이면
			questionList = this.getComQuestionIdList();
		}

		//현재 날짜 설정
		LocalDateTime now = LocalDateTime.now();

		Long questionId = this.getPresentQuestionId(questionList, now);

		Question tmpQuestion = questionRepository.findById(questionId).get();

		// MachineDetailResponse 객체 만들기
		MachineDetailResponse tmp = new MachineDetailResponse(tmpMachine,tmpQuestion);

		// 혼잡도 계산

		// 일단 어제 기준으로 해보고
		// 시간 남으면 지난주 평균으로 해보기!

		//지난주 날짜 설정
		LocalDateTime startOfYes = now.minusDays(1).with(LocalTime.MIN);

		// plusMinutes(숫자) method -> 15분씩 더해줌
		// 그래서 내 생각은...
		// 배열 인덱스만큼 for문 돌려서 countByVoteDate 메소드 실행시켜서 배열에 넣어주자

		// 먼저 MachineDetailResponse의 배열 생성
		long[] tmpArr = new long[96];

		Timestamp startTime = Timestamp.valueOf(startOfYes);
		Timestamp endTime = Timestamp.valueOf(startOfYes.plusMinutes(15)); // 15분씩 더하기

		for(int idx = 0; idx < 96; idx++) {

			System.out.println("startDate: "+startTime);
			System.out.println("endDate: "+endTime);

			tmpArr[idx] = voteRepository.countByVoteDate(startTime, endTime); // 배열에 넣어주고

			// startTime endTime으로 갱신
			startTime = endTime;

			LocalDateTime tmpDate = startTime.toLocalDateTime();
			endTime = Timestamp.valueOf(tmpDate.plusMinutes(15));

		}
		tmp.setUserCount(tmpArr);

		System.out.println(tmp.getUserCount());

		// 이번주 23:59:59 -> 끝
		// 저번주 00:00:00 -> 시작
		Timestamp lastWeek = Timestamp.valueOf(now.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).minusDays(6).with(LocalDateTime.MIN));
		Timestamp thisWeek = Timestamp.valueOf(now.with(LocalDateTime.MAX));

		System.out.println("lastWeek: "+lastWeek);
		System.out.println("thisWeek: "+thisWeek);

		// answerA, answerB
		Long answerA = voteRepository.countByMachineNoAndQuestionIdAndAnswerAndStartDayAndEndDay(machineNo, questionId,0,lastWeek,thisWeek);
		Long answerB = voteRepository.countByMachineNoAndQuestionIdAndAnswerAndStartDayAndEndDay(machineNo, questionId,1,lastWeek,thisWeek);

		System.out.println("service의 answerA: "+answerA);
		System.out.println("service의 answerB: "+answerB);

		tmp.setAnswerA(answerA);
		tmp.setAnswerB(answerB);

		return tmp;
	}

	// 관심 기기 등록
	@Override
	public void insertFavoriteMachine(String email, Long machineNo) {
		// FavoriteMachine entity에 값을 넣으려면 user, machine 객체가 필요
		// machine
		Machine machine = machineRepository.findById(machineNo).orElseThrow();

		//user
		User user = userRepository.findByEmail(email).orElseThrow();

		// // save메소드에 넣어줄 FavoriteMachine 객체 만들기
		// FavoriteMachine favoriteMachine = entity.toEntity(user, machine);

		// favoriteMachineRepository.save(favoriteMachine);

	}

	// 관심 기기 삭제
	@Override
	public void deleteFavoriteMachine(String email, Long machineNo) {
		// FavoriteMachine 객체 받아오기
		FavoriteMachine favoriteMachine = favoriteMachineRepository.findByMachine_MachineNo(machineNo)
			.orElseThrow();

		favoriteMachineRepository.delete(favoriteMachine);

	}
}
