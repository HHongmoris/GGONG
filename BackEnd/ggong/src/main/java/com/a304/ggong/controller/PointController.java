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

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/points")
public class PointController {

    private final PointService pointService;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<PointListResponse>> getAllPoints(@RequestHeader(required = true, name = "Authorization") String token) throws NullPointerException{
        //성민
        String email = jwtService.extractEmailTest(token);
        if(email==null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        // 원래는 IoT에 꽁초가 인식되면 vote table과 point table에 데이터가 쌓이고, point table에 데이터가 쌓이면 잔여포인트 계산해서 update하는 식으로 짜면 될 것 같은데
        // 로직 테스트 위해서 포인트 페이지에 들어가면 잔여 포인트가 계산되는 것으로 가정
        // 잔여 포인트 계산하는 메소드 호출

        LocalDateTime now = LocalDateTime.now();
        Timestamp day = Timestamp.valueOf(now);

        // pointService.calculateBalancePoint(email, day);

        List<PointListResponse> list =pointService.selectPointAll(email);

        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    //포인트 내역 조회
    @GetMapping("/point-list")
    public ResponseEntity<List<PointListResponse>> pointList(@RequestHeader(required = true, name = "Authorization") String token, @RequestParam String start, @RequestParam String end){
        //성민
        String email = jwtService.extractEmailTest(token);
        if(email==null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // 원래는 IoT에 꽁초가 인식되면 vote table과 point table에 데이터가 쌓이고, point table에 데이터가 쌓이면 잔여포인트 계산해서 update하는 식으로 짜면 될 것 같은데
        // 로직 테스트 위해서 포인트 페이지에 들어가면 잔여 포인트가 계산되는 것으로 가정
        // 잔여 포인트 계산하는 메소드 호출
//        start = start.replaceAll("\"","");
//        end = end.replaceAll("\"","");
        Timestamp endtime = Timestamp.valueOf(end + " 23:59:59.9");

        // pointService.calculateBalancePoint(email, endtime);

        List<PointListResponse> points = pointService.selectPointListByUserEmailAndDate(email, start, end);

        return ResponseEntity.status(HttpStatus.OK).body(points);

    }
}
