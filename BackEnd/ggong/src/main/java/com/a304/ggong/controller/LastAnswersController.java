package com.a304.ggong.controller;

import com.a304.ggong.dto.response.AllAnswerResponse;
import com.a304.ggong.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/answers")
public class LastAnswersController {

    @Autowired
    private AnswerService answerService;

    // 모든 질문 응답 데이터 조회
    @GetMapping
    public ResponseEntity<List<AllAnswerResponse>[]> getAllAnswers (){
        List<AllAnswerResponse>[] result = new List[3];

        // 여기는 숫자 바뀌는 것 구현해야함. (따로 메소드화 할 것!)
        int questionGroup = 1;

        List<AllAnswerResponse> commonAnswers = answerService.selectAnswersGroupByCommon(questionGroup);
        List<AllAnswerResponse> uniAnswers = answerService.selectAnswersGroupByUnis(questionGroup);
        List<AllAnswerResponse> comAnswers = answerService.selectAnswersGroupByCompanies(questionGroup);

        for(int idx = 0; idx < 3; idx++){
            result[idx] = new ArrayList<>();
        }

        // 각 value 배열에 넣어주기
        result[0] = commonAnswers;
        result[1] = uniAnswers;
        result[2] = comAnswers;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 질문 응답 상세페이지

}
