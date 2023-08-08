package com.a304.ggong.controller;

import com.a304.ggong.dto.response.AllAnswerResponse;
import com.a304.ggong.dto.response.AnswerDetailResponse;
import com.a304.ggong.global.resource.QuestionGroup;
import com.a304.ggong.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/answers/sse")
public class PresentAnswersController {

    @Autowired
    private AnswerService answerService;

    private SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);

    QuestionGroup questionGroup = new QuestionGroup();
    int questionGroupNum = questionGroup.getThisWeekGroupNum();

//    public void SseController(SseEmitter sseEmitters){
//        this.sseEmitter = sseEmitters;
//    }

    // 모든 질문 응답 데이터 조회
    // 이부분 path를 다르게 줘야하나...?
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<java.util.List<AllAnswerResponse>[]> getAllAnswers (){
//        SseEmitter emitter = new SseEmitter();
//        sseEmitter.add(emitter);

        List<AllAnswerResponse>[] result = new java.util.List[3];

        List<AllAnswerResponse> commonAnswers = answerService.selectAnswersGroupByCommon(questionGroupNum);
        List<AllAnswerResponse> uniAnswers = answerService.selectAnswersGroupByUnis(questionGroupNum);
        List<AllAnswerResponse> comAnswers = answerService.selectAnswersGroupByCompanies(questionGroupNum);

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
    // 대학Path
    @GetMapping(value = "/uni", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<List<AnswerDetailResponse>[]> getUniAnswersDetail(){
        List<AnswerDetailResponse>[] result = new List[3];

        for(int idx = 0; idx < 3; idx++){
            result[idx] = new ArrayList<>();
        }

        result = answerService.selectDetailAnswer(questionGroupNum, "대학");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 질문 응답 상세페이지
    // 기업Path
    @GetMapping("/com")
    public ResponseEntity<List<AnswerDetailResponse>[]> getComAnswersDetail(){
        List<AnswerDetailResponse>[] result = new List[3];

        for(int idx = 0; idx < 3; idx++){
            result[idx] = new ArrayList<>();
        }

        result = answerService.selectDetailAnswer(questionGroupNum, "기업");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
