package com.a304.ggong.controller;

import com.a304.ggong.dto.response.PointListResponse;
import com.a304.ggong.global.jwt.service.JwtService;
import com.a304.ggong.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/points")
public class PointController {

    private final PointService pointService;
    private final JwtService jwtService;

    //포인트 내역 조회
    @GetMapping
    public ResponseEntity<List<PointListResponse>> pointList(@RequestHeader(required = true) String token, @RequestParam String start, @RequestParam String end){
        //성민
        String email = jwtService.extractEmailTest(token);
        if(email.equals("")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

//        // token에서 email 빼오기
//        Optional<String> opEmail = jwtService.extractEmail(token);
//
//        if (opEmail.isEmpty()) { // optional Email이 null이라면 토큰이 유효하지 않다는 소리
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//
//        // 토큰이 유효하다면? email 추출
//        String email = opEmail.get();

        List<PointListResponse> points = pointService.selectPointListByUserEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(points);

    }
}
