package com.a304.ggong.controller;

import com.a304.ggong.dto.response.AllAnswerResponse;
import com.a304.ggong.dto.response.AllMachinesResponse;
import com.a304.ggong.dto.response.LikeResponse;
import com.a304.ggong.service.MachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/machines")

public class MachineController {
    private final MachineService machineService;

    // 모든 기기 조회
    @GetMapping
    public ResponseEntity<List<AllMachinesResponse>> machineList(){
        List<AllMachinesResponse> machineList = machineService.getAllMachines();
        return ResponseEntity.status(HttpStatus.OK).body(machineList);
    }

    // 관심 기기 목록
    @GetMapping("/like")
    public ResponseEntity<List<LikeResponse>> likeMachineList(){

    }

    // 특정 기기의 상세 정보 조회

    // 관심 기기 등록

    // 관심 기기 삭제
}