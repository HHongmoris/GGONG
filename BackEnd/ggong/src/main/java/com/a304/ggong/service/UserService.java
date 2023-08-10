package com.a304.ggong.service;

import javax.transaction.Transactional;

import com.a304.ggong.dto.request.UserCigarRequest;
import com.a304.ggong.dto.response.MachineDetailResponse;
import com.a304.ggong.dto.response.SmokeCountResponse;
import com.a304.ggong.dto.response.UserCigarResponse;
import com.a304.ggong.entity.FavoriteMachine;
import com.a304.ggong.repository.FavoriteMachineRepository;
import com.a304.ggong.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.a304.ggong.dto.UserSignUpDto;
import com.a304.ggong.entity.Role;
import com.a304.ggong.entity.User;
import com.a304.ggong.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final VoteRepository voteRepository;
	private final FavoriteMachineRepository favoriteMachineRepository;

	@Autowired
	private final MachineService machineService;

	public void signUp(UserSignUpDto userSignUpDto) throws Exception {
		if (userRepository.findByEmail(userSignUpDto.getEmail()).isPresent()) {
			throw new Exception("이미 존재하는 이메일입니다.");
		}

		User user = User.builder()
			.email(userSignUpDto.getEmail())
			.password(userSignUpDto.getPassword())
			.name(userSignUpDto.getName())
			.ageRange(userSignUpDto.getAgeRange())
			.gender(userSignUpDto.getGender())
			.favoriteCigarette(userSignUpDto.getFavoriteCigarette())
			.QR(userSignUpDto.getQR())
			.userRating(userSignUpDto.getUserRating())
			.role(Role.USER)
			.build();

		user.passwordEncode(passwordEncoder);
		userRepository.save(user);

	}

	// 오늘, 어제 넣은 꽁초 개수 조회
	public SmokeCountResponse selectVote(String email){
		// 먼저, 유저email로 유저객체 찾기
		User user = userRepository.findByEmail(email).get();

		// UserNo뽑기
		Long userNo = user.getUserNo();

		// 오늘
		LocalDateTime now = LocalDateTime.now();

		// 어제
		LocalDateTime yesterday = now.minusDays(1);

		// Timestamp로 변환
		Timestamp startNowDate = Timestamp.valueOf(now.with(LocalTime.MIN));
		Timestamp endNowDate = Timestamp.valueOf(now.with(LocalTime.MAX));

		Timestamp startYesDate = Timestamp.valueOf(yesterday.with(LocalTime.MIN));
		Timestamp endYesDate = Timestamp.valueOf(yesterday.with(LocalTime.MAX));

		System.out.println("이것은 서비스의 startNowDate입니다. "+startNowDate);
		System.out.println("이것은 서비스의 endNowDate입니다. "+endNowDate);
		System.out.println("이것은 서비스의 startYesDate입니다. "+startYesDate);
		System.out.println("이것은 서비스의 endYesDate입니다. "+endYesDate);

		SmokeCountResponse tmp = new SmokeCountResponse();

		// 오늘
		tmp.setCurrentCount(voteRepository.countByVoteDateAndUserId(startNowDate,endNowDate,userNo));

		// 어제
		tmp.setPastCount(voteRepository.countByVoteDateAndUserId(startYesDate,endYesDate,userNo));

		System.out.println("이것은 service의 tmp now: "+tmp.getCurrentCount());

		return null;

	}

	// 회원 관심 기기 데이터 조회
	public MachineDetailResponse[] selectLikeMachine(String email){
		// 먼저, Email을 이용해 유저 객체 가져와야함.
		User user = userRepository.findByEmail(email).orElseThrow();

		List<FavoriteMachine> favoriteMachine = favoriteMachineRepository.findByUser_UserNo(user.getUserNo());

		MachineDetailResponse[] arr = new MachineDetailResponse[favoriteMachine.size()];

		for(int idx = 0; idx < favoriteMachine.size(); idx++){
			arr[idx] = machineService.selectMachineDetail(favoriteMachine.get(idx).getMachine().getMachineNo());
		}

		return arr;
	}

	// 사용 담배 수정
	public UserCigarResponse updateCiga (String email, UserCigarRequest request){
		User user = userRepository.findByEmail(email).get();
		user.setFavoriteCigarette(request.getFavoriteCigarette());
		// .save하면 update로 돼...? 자동으로...?
		userRepository.save(user);

		UserCigarResponse response = new UserCigarResponse();
		response.setFavoriteCigarette(request.getFavoriteCigarette());
		return response;
	}

	// 회원 탈퇴
	public void deleteUser(String email){
		User user = userRepository.findByEmail(email).get();
		userRepository.delete(user);
	}
}
