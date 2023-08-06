package com.a304.ggong.controller;

import com.a304.ggong.dto.request.LikeDeleteRequest;
import com.a304.ggong.dto.request.LikeRegistRequest;
import com.a304.ggong.dto.response.AllAnswerResponse;
import com.a304.ggong.dto.response.AllMachinesResponse;
import com.a304.ggong.dto.response.LikeResponse;
import com.a304.ggong.dto.response.MachineDetailResponse;
import com.a304.ggong.service.MachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/machines")

public class MachineController {

    @Autowired
    private final MachineService machineService;

    // 모든 기기 조회
    @GetMapping
    public ResponseEntity<List<AllMachinesResponse>> getMachineList(){
        List<AllMachinesResponse> machineList = machineService.selectAllMachines();
        return new ResponseEntity<>(machineList, HttpStatus.OK);
    }

//    // 관심 기기 목록
//    @GetMapping("/like")
//    public ResponseEntity<List<LikeResponse>> likeMachineList(@RequestHeader(required = true)String token){
        //try {
        //			return new ResponseEntity<Article>(articleService.getArticle(articleSeq), HttpStatus.OK);
        //		} catch (Exception e) {
        //			throw new ArticleNotFoundException(articleSeq + "번 게시글은 없습니다.");
        //		}
//    }

    // 특정 기기의 상세 정보 조회
    @GetMapping("/{machineId}")
    public ResponseEntity<MachineDetailResponse> getMachineDetailInfo(@PathVariable Long machineId){
        MachineDetailResponse machineDetailResponse = machineService.selectMachineDetail(machineId);
        return new ResponseEntity<MachineDetailResponse>(machineDetailResponse,HttpStatus.OK);
    }

    // 관심 기기 등록
    @PostMapping
    public ResponseEntity<List<LikeResponse>> registLikeMachine(@RequestHeader String token, LikeRegistRequest request){
        // token에서 email 빼오기
        String email = null;
        machineService.insertFavoriteMachine(email, request);

        // 관심 기기 새로 업데이트 해서 프론트에 주기
        List<LikeResponse> likes = machineService.selectAllFavoriteMachines(email);
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    // 관심 기기 삭제
    @DeleteMapping
    public ResponseEntity<List<LikeResponse>> deleteLikeMachine(@RequestHeader String token, LikeDeleteRequest request){
        machineService.deleteFavoriteMachine(request);

        // token에서 email 빼오기
        String email = null;
        // 관심 기기 새로 업데이트 해서 프론트에 주기
        List<LikeResponse> likes = machineService.selectAllFavoriteMachines(email);
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }
}