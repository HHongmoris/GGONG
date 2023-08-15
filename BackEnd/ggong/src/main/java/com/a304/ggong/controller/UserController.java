package com.a304.ggong.controller;

import com.a304.ggong.dto.request.UserCigarRequest;
import com.a304.ggong.dto.response.*;
import com.a304.ggong.entity.User;
import com.a304.ggong.global.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.a304.ggong.dto.UserSignUpDto;
import com.a304.ggong.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@Autowired
	private final JwtService jwtService;

	@PostMapping("/sign-up")
	public String signUp(@RequestBody UserSignUpDto userSignUpDto) throws Exception {
		userService.signUp(userSignUpDto);
		return "회원가입 성공";
	}

	@GetMapping("/jwt-test")
	public String jwtTest() {
		return "jwtTest 요청 성공";
	}

	// // 로그아웃
	// @PostMapping("/logout")
	// public ResponseEntity<Boolean> signOut(@RequestHeader String token){
	//
	// 	return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	// }

	// 오늘, 어제 넣은 꽁초 개수 조회
	@GetMapping("/smoke")
	public ResponseEntity<SmokeCountResponse> getCiga(@RequestHeader(required = true, name = "Authorization") String token){
		//성민
		String email = jwtService.extractEmailTest(token);

		if(email==null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		SmokeCountResponse tmp = userService.selectVote(email);

		return new ResponseEntity<>(tmp, HttpStatus.OK);
	}

	// 회원 관심 기기 데이터 조회
	@GetMapping("/like")
	public ResponseEntity<MachineDetailResponse[]> getLikeMachine(@RequestHeader(required = true, name = "Authorization") String token){
		//성민
		String email = jwtService.extractEmailTest(token);
		if(email == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		MachineDetailResponse[] tmp = userService.selectLikeMachine(email);

		return  new ResponseEntity<>(tmp,HttpStatus.OK);
	}

	// 사용 담배 수정
	@PutMapping("/cigar")
	public ResponseEntity<UserCigarResponse> changeUserCiga (@RequestHeader(required = true, name = "Authorization") String token,
															 UserCigarRequest request){
		//성민
		String email = jwtService.extractEmailTest(token);
		if(email==null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		UserCigarResponse tmp = userService.updateCiga(email, request);

		return new ResponseEntity<>(tmp, HttpStatus.OK);
	}

	// 회원 탈퇴
	@DeleteMapping("/Deprecated")
	public ResponseEntity<?> deprecatedUser(@RequestHeader(required = true, name = "Authorization") String token){
		//성민
		String email = jwtService.extractEmailTest(token);
		if(email==null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		userService.deleteUser(email);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> getUser(@RequestHeader(required = true, name = "Authorization") String token){
		// 병기
		String email = jwtService.extractEmailTest(token);
		if(email==null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(userService.selectUser(email), HttpStatus.OK);
	}

}
