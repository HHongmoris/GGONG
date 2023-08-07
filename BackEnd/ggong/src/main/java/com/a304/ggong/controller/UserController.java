package com.a304.ggong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a304.ggong.dto.UserSignUpDto;
import com.a304.ggong.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@PostMapping("/sign-up")
	public String signUp(@RequestBody UserSignUpDto userSignUpDto) throws Exception {
		userService.signUp(userSignUpDto);
		return "회원가입 성공";
	}

	@GetMapping("/jwt-test")
	public String jwtTest() {
		return "jwtTest 요청 성공";
	}

	// 최근 포인트 조회는 list로 받은 포인트 객체에서 인덱스로 조정
	@GetMapping
}
