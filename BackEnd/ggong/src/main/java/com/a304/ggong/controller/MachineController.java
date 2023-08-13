package com.a304.ggong.controller;

import java.io.IOException;
import java.util.List;

import com.a304.ggong.global.sseemitter.SseEmitters;
import com.a304.ggong.service.SseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.a304.ggong.dto.response.AllMachinesResponse;
import com.a304.ggong.dto.response.LikeResponse;
import com.a304.ggong.dto.response.MachineDetailResponse;
import com.a304.ggong.exception.FavoriteMachineNotFoundException;
import com.a304.ggong.global.jwt.service.JwtService;
import com.a304.ggong.service.MachineService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/machines")

public class MachineController {

	@Autowired
	private final MachineService machineService;

	@Autowired
	private final JwtService jwtService;

	// SSE
	private final SseEmitters sseEmitters;
	private final SseService service;

	// 모든 기기 조회
	@GetMapping
	public ResponseEntity<List<AllMachinesResponse>> getMachineList() {
		List<AllMachinesResponse> machineList = machineService.selectAllMachines();
		return new ResponseEntity<>(machineList, HttpStatus.OK);
	}

	//성민 시도 - 안되면 이 블럭 삭제
	@GetMapping("/like")
	public ResponseEntity<Object> likeMachineList(@RequestHeader(required = true, name = "Authorization") String token) {
		String email = jwtService.extractEmailTest(token);
		if(email==null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		List<LikeResponse> likeList = machineService.selectAllFavoriteMachines(email);

		if (likeList == null) {
			FavoriteMachineNotFoundException favoriteMachineNotFoundException = new FavoriteMachineNotFoundException();
			return new ResponseEntity<>(favoriteMachineNotFoundException, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Object>(likeList, HttpStatus.OK);
	}

	// SSE 이식 (현재 질문과 답변)
	// 특정 기기의 상세 정보 조회
	@GetMapping(value = "/{machineNo}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<SseEmitter> getMachineDetailInfo(@PathVariable("machineNo") Long machineNo) {
		SseEmitter emitter = new SseEmitter();
		sseEmitters.add(emitter);

		MachineDetailResponse machineDetailResponse = machineService.selectMachineDetail(machineNo);

		try{
			emitter.send(SseEmitter.event().name("detail").data(machineDetailResponse));
		}catch (IOException e){
			e.printStackTrace();
		}
		return ResponseEntity.ok(emitter);
	}

	// 관심 기기 등록
	@PostMapping("/{machineNo}")
	public ResponseEntity<List<LikeResponse>> registLikeMachine(@RequestHeader(required = true, name = "Authorization") String token, @PathVariable("machineNo") Long machineNo) {
		//성민
		String email = jwtService.extractEmailTest(token);
		if(email==null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		machineService.insertFavoriteMachine(email, machineNo);

		// 관심 기기 새로 업데이트 해서 프론트에 주기
		List<LikeResponse> likes = machineService.selectAllFavoriteMachines(email);
		return new ResponseEntity<>(likes, HttpStatus.OK);
	}

	// 관심 기기 삭제
	@DeleteMapping("/{machineNo}")
	public ResponseEntity<List<LikeResponse>> deleteLikeMachine(@RequestHeader(required = true, name = "Authorization") String token,
		@PathVariable("machineNo") Long machineNo) {
		String email = jwtService.extractEmailTest(token);
		if(email==null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		machineService.deleteFavoriteMachine(email,machineNo);

		// 관심 기기 새로 업데이트 해서 프론트에 주기
		List<LikeResponse> likes = machineService.selectAllFavoriteMachines(email);
		return new ResponseEntity<>(likes, HttpStatus.OK);
	}
}
