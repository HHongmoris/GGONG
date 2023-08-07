package com.a304.ggong.service;

import java.util.List;
import java.util.stream.Collectors;

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
			User user = userRepository.findByEmail(email).orElseThrow();

			List<LikeResponse> list = favoriteMachineRepository.findByUserNo(user.getUserNo())
				.stream()
				.map(LikeResponse::new)
				.collect(
					Collectors.toList());
			return list; // return문을 여기에 쓰는 게 맞나..?

	}

	// 기기 상세 정보
	@Override
	public MachineDetailResponse selectMachineDetail(Long machineId) {
		// MachineDetailResponse 생성자에는 machine, question 객체가 필요해
		// question 객체를 받으려면 vote객체를 타야해

		// 먼저 machineID로 machine 객체를 찾아
		Machine machine = machineRepository.findById(machineId).orElseThrow();

		// 그리고 machineID로 vote를 찾아
		Vote vote = voteRepository.findByMachineId(machineId).orElseThrow();

		// 찾은 vote객체로 question 객체를 찾아
		Question question = questionRepository.findById(vote.getQuestion().getQuestionID()).orElseThrow();

		return new MachineDetailResponse(machine, question);
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
		FavoriteMachine favoriteMachine = favoriteMachineRepository.findByMachineNo(entity.getMachineNo())
			.orElseThrow();

		favoriteMachineRepository.delete(favoriteMachine);

	}
}
