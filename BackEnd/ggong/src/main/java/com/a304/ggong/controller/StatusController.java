package com.a304.ggong.controller;

import com.a304.ggong.dto.response.*;
import com.a304.ggong.global.sseemitter.SseEmitters;
import com.a304.ggong.service.SseService;
import com.a304.ggong.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/stat")
public class StatusController {

    @Autowired
    private final StatusService statusService;

    // SSE
    private final SseEmitters sseEmitters;
    private final SseService service;

    // SSE
    //당일 수거함 사용자 조회
    @GetMapping("/today")
    public ResponseEntity<SseEmitter> todayUser(){
        SseEmitter emitter = new SseEmitter(5000L);
        sseEmitters.add(emitter);

        TodayUserResponse todayUserResponse = statusService.selectUserCnt();

        try{
            emitter.send(SseEmitter.event().name("todayUsers").data(todayUserResponse));
        }catch (IOException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(emitter);
    }

    //사용자 통계 데이터 조회
    @GetMapping("/user")
    public ResponseEntity<AllUserResponse> allUser(){
        AllUserResponse allUserResponse = statusService.selectAllUserCnt();
        return ResponseEntity.status(HttpStatus.OK).body(allUserResponse);
    }


    //연령대별 통계 데이터 조회
    @GetMapping("/age")
    public ResponseEntity<List<AgeStatResponse>> ageStatus(){
        List<AgeStatResponse> ageStat = statusService.selectUserByAgeCnt();
        return ResponseEntity.status(HttpStatus.OK).body(ageStat);
    }

    //성별 통계 데이터 조회
    @GetMapping("/gender")
    public ResponseEntity<List<GenderStatResponse>> genderStatus(){
        List<GenderStatResponse> genderStat = statusService.selectUserByGenderCnt();
        return ResponseEntity.status(HttpStatus.OK).body(genderStat);
    }

    //기기별 통계 데이터 조회
    @GetMapping("/machine")
    public ResponseEntity<List<MachineStatResponse>> machineStatus(){
        List<MachineStatResponse> machineStat = statusService.selectUserByMachine();
        return ResponseEntity.status(HttpStatus.OK).body(machineStat);
    }
}
