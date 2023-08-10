package com.a304.ggong.controller;

import com.a304.ggong.dto.response.AllAnswerResponse;
import com.a304.ggong.dto.response.AnswerDetailResponse;
import com.a304.ggong.global.resource.QuestionGroup;
import com.a304.ggong.global.sseemitter.SseEmitters;
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
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/answers/sse")
public class PresentAnswersController {

    @GetMapping("")
    public String index(){
        return "index";
    }

    @Autowired
    private AnswerService answerService;

    private ExecutorService nonBlockingService = Executors.newCachedThreadPool();


//    public void SseController(SseEmitter sseEmitters){
//        this.sseEmitter = sseEmitters;
//    }

    // 모든 질문 응답 데이터 조회
    // 이부분 path를 다르게 줘야하나...?
    @GetMapping(path = "", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter getAllAnswers (){
        SseEmitter emitter = new SseEmitter(86400000L);


        QuestionGroup questionGroup = new QuestionGroup();
        int questionGroupNum = questionGroup.getThisWeekGroupNum();

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

        nonBlockingService.execute(()->{
            try{
                emitter.send(SseEmitter.event().name("allAnswers").data(result));
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
                e.printStackTrace();
            }
        });

        // 유저별로 sse줘야하는 경우 -> 포인트...?
        // sseEmitter.onCompletion(() -> NotificationController.'Map이름'.remove(userId));
        // sseEmitter.onTimeout(() -> NotificationController.'Map이름'.remove(userId));
        // sseEmitter.onError((e) -> NotificationController.'Map이름'.remove(userId));
        return emitter;
    }

    // 질문 응답 상세페이지
    // 대학Path
    @GetMapping(value = "/uni", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<List<AnswerDetailResponse>[]> getUniAnswersDetail(){
        List<AnswerDetailResponse>[] result = new List[3];

        QuestionGroup questionGroup = new QuestionGroup();
        int questionGroupNum = questionGroup.getThisWeekGroupNum();

        for(int idx = 0; idx < 3; idx++){
            result[idx] = new ArrayList<>();
        }

        result = answerService.selectDetailAnswer(questionGroupNum, "대학");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 질문 응답 상세페이지
    // 기업Path
    @GetMapping(value ="/com", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<List<AnswerDetailResponse>[]> getComAnswersDetail(){
        List<AnswerDetailResponse>[] result = new List[3];


        QuestionGroup questionGroup = new QuestionGroup();
        int questionGroupNum = questionGroup.getThisWeekGroupNum();

        for(int idx = 0; idx < 3; idx++){
            result[idx] = new ArrayList<>();
        }

        result = answerService.selectDetailAnswer(questionGroupNum, "기업");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
