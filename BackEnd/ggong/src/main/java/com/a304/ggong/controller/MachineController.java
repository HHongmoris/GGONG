package com.a304.ggong.controller;

import java.util.List;
import java.util.Optional;

import com.a304.ggong.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.a304.ggong.dto.request.LikeDeleteRequest;
import com.a304.ggong.dto.request.LikeRegistRequest;
import com.a304.ggong.dto.response.AllMachinesResponse;
import com.a304.ggong.dto.response.LikeResponse;
import com.a304.ggong.dto.response.MachineDetailResponse;
import com.a304.ggong.exception.FavoriteMachineNotFoundException;
import com.a304.ggong.global.jwt.service.JwtService;
import com.a304.ggong.service.MachineService;

import lombok.RequiredArgsConstructor;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/machines")

public class MachineController {

	@Autowired
	private final MachineService machineService;

	@Autowired
	private final JwtService jwtService;

	// 모든 기기 조회
	@GetMapping
	public ResponseEntity<List<AllMachinesResponse>> getMachineList() {
		List<AllMachinesResponse> machineList = machineService.selectAllMachines();
		return new ResponseEntity<>(machineList, HttpStatus.OK);
	}

	// 관심 기기 목록
	@GetMapping("/like")
	public ResponseEntity<Object> likeMachineList(@AuthenticationPrincipal String email) {
//	public ResponseEntity<Object> likeMachineList(@RequestHeader(required = true, name = "Authorization") String token) {
//		System.out.println(token);
//		// token에서 email 빼오기
//		Optional<String> opEmail = jwtService.extractEmail(token);
//
//		if (opEmail.isEmpty()) { // optional Email이 null이라면 토큰이 유효하지 않다는 소리
//			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//		}
//
//		String email = opEmail.get();
//		System.out.println("이메일"+email);

//		System.out.println(user.toString());
//		String email = user.getEmail();
		System.out.println("email: " + email);
		List<LikeResponse> likeList = machineService.selectAllFavoriteMachines(email);

		if (likeList == null) {
			FavoriteMachineNotFoundException favoriteMachineNotFoundException = new FavoriteMachineNotFoundException();
			return new ResponseEntity<>(favoriteMachineNotFoundException, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>(likeList, HttpStatus.OK);
	}

	// 특정 기기의 상세 정보 조회
	@GetMapping("/{machineId}")
	public ResponseEntity<MachineDetailResponse> getMachineDetailInfo(@PathVariable Long machineId) {
		MachineDetailResponse machineDetailResponse = machineService.selectMachineDetail(machineId);
		return new ResponseEntity<MachineDetailResponse>(machineDetailResponse, HttpStatus.OK);
	}

	// 관심 기기 등록
	@PostMapping
	public ResponseEntity<List<LikeResponse>> registLikeMachine(@RequestHeader String token,
		LikeRegistRequest request) {
		// token에서 email 빼오기
		Optional<String> opEmail = jwtService.extractEmail(token);

		if (opEmail.isEmpty()) { // optional Email이 null이라면 토큰이 유효하지 않다는 소리
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		// 토큰이 유효하다면? email 추출
		String email = opEmail.get();
		machineService.insertFavoriteMachine(email, request);

		// 관심 기기 새로 업데이트 해서 프론트에 주기
		List<LikeResponse> likes = machineService.selectAllFavoriteMachines(email);
		return new ResponseEntity<>(likes, HttpStatus.OK);
	}

	// 관심 기기 삭제
	@DeleteMapping
	public ResponseEntity<List<LikeResponse>> deleteLikeMachine(@RequestHeader String token,
		LikeDeleteRequest request) {
		machineService.deleteFavoriteMachine(request);

		Optional<String> opEmail = jwtService.extractEmail(token);
		if (opEmail.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		// token에서 email 빼오기
		String email = opEmail.get();
		// 관심 기기 새로 업데이트 해서 프론트에 주기
		List<LikeResponse> likes = machineService.selectAllFavoriteMachines(email);
		return new ResponseEntity<>(likes, HttpStatus.OK);
	}
}
