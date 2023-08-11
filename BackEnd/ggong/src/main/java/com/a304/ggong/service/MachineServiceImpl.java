package com.a304.ggong.service;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.zaxxer.hikari.util.ClockSource;
import org.springframework.stereotype.Service;

import com.a304.ggong.dto.request.LikeDeleteRequest;
import com.a304.ggong.dto.request.LikeRegistRequest;
import com.a304.ggong.dto.response.AllMachinesResponse;
import com.a304.ggong.dto.response.LikeResponse;
import com.a304.ggong.dto.response.MachineDetailResponse;
import com.a304.ggong.entity.FavoriteMachine;
import com.a304.ggong.entity.Machine;
import com.a304.ggong.entity.Question;
import com.a304.ggong.entity.User;
import com.a304.ggong.entity.Vote;
import com.a304.ggong.exception.FavoriteMachineNotFoundException;
import com.a304.ggong.repository.FavoriteMachineRepository;
import com.a304.ggong.repository.MachineRepository;
import com.a304.ggong.repository.QuestionRepository;
import com.a304.ggong.repository.UserRepository;
import com.a304.ggong.repository.VoteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

	// 기기 상세 정보
	@Override
	public MachineDetailResponse selectMachineDetail(Long machineNo) {
		// MachineDetailResponse 생성자에는 machine, question 객체가 필요해
		// question 객체를 받으려면 vote객체를 타야해

		// 먼저 machineID로 machine 객체를 찾아
		Machine machine = machineRepository.findById(machineNo).get();

		System.out.println("machineName: "+machine.getName());
		System.out.println(machineNo.getClass().getName());

		// 그리고 machineNo로 vote를 찾아
		List<Vote> vote = voteRepository.findByMachine_MachineNo(machineNo);

//		System.out.println("voteNo: " + vote.getVoteNo());
//
//		// 찾은 vote객체로 question 객체를 찾아
//		Question question = questionRepository.findById(vote.getQuestion().getQuestionID()).get();

//		System.out.println("questionNo: " + question.getQuestionID());

		// 이렇게 찾으면 안되고...
		//

//		MachineDetailResponse tmp = new MachineDetailResponse(machine, question);

		// 혼잡도 계산해주기

		//현재 날짜 설정
		LocalDateTime now = LocalDateTime.now();

		//지난주 날짜 설정
		LocalDateTime startOfLastWeek = now.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).minusDays(6).with(LocalDateTime.MIN);
		LocalDateTime endOfLastWeek = now.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).with(LocalDateTime.MAX);

		// plusMinutes(숫자) method -> 15분씩 더해줌
		// 그래서 내 생각은...
		// 배열 인덱스만큼 for문 돌려서 countByVoteDate 메소드 실행시켜서 배열에 넣어주자

		// 먼저 MachineDetailResponse의 배열 생성
//		tmp.setUserCount();
		long[] tmpArr = new long[96];

		for(int idx = 0; idx < 96; idx++) {

			//Timestamp로 변환
			Timestamp startDate = Timestamp.valueOf(startOfLastWeek);
			Timestamp endDate = Timestamp.valueOf(endOfLastWeek.plusMinutes(15)); // 15분씩 더하기

			tmpArr[idx] = voteRepository.countByVoteDate(startDate, endDate); // 배열에 넣어주고

			// startOfLastWeek endOfLastWeek로 갱신
			startOfLastWeek = endOfLastWeek;

		}
//		tmp.setUserCount(tmpArr);

		// answerA, answerB
//		Long answerA = voteRepository.countByQuestionGroupAndAnswerTypeAndQuestionType(question.getGroup(), 0, question.getType().toString());
//		Long answerB = voteRepository.countByQuestionGroupAndAnswerTypeAndQuestionType(question.getGroup(), 1, question.getType().toString());
//
//		tmp.setAnswerA(answerA);
//		tmp.setAnswerB(answerB);

		return null;
	}

	// 관심 기기 등록
	@Override
	public void insertFavoriteMachine(String email, LikeRegistRequest entity) {
		// FavoriteMachine entity에 값을 넣으려면 user, machine 객체가 필요
		// machine
		Machine machine = machineRepository.findById(entity.getMachineNo()).orElseThrow();

		//user
		User user = userRepository.findByEmail(email).orElseThrow();

		// save메소드에 넣어줄 FavoriteMachine 객체 만들기
		FavoriteMachine favoriteMachine = entity.toEntity(user, machine);

		favoriteMachineRepository.save(favoriteMachine);

	}

	// 관심 기기 삭제
	@Override
	public void deleteFavoriteMachine(LikeDeleteRequest entity) {
		// FavoriteMachine 객체 받아오기
		FavoriteMachine favoriteMachine = favoriteMachineRepository.findByMachine_MachineNo(entity.getMachineNo())
			.orElseThrow();

		favoriteMachineRepository.delete(favoriteMachine);

	}
}
