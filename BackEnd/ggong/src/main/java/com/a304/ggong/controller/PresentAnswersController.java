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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/answers/present")
public class PresentAnswersController {

    @Autowired
    private AnswerService answerService;

    // SSE
    private final SseEmitters sseEmitters;

    // 테스트용
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> getAllAnswers (){
        SseEmitter emitter = new SseEmitter();
        sseEmitters.add(emitter);

            try{
                QuestionGroup questionGroup = new QuestionGroup();
                int questionGroupNum = questionGroup.getThisWeekGroupNum();

                // voteTable 갱신
                answerService.iniAnswers();

                List<AllAnswerResponse>[] result = new List[3];

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

                emitter.send(SseEmitter.event().name("allAnswers").data(result));
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
                e.printStackTrace();
            }

        emitter.onCompletion(emitter::complete);
        emitter.onTimeout(emitter::complete);

        // 유저별로 sse줘야하는 경우 -> 포인트...?
        // sseEmitter.onCompletion(() -> NotificationController.'Map이름'.remove(userId));
        // sseEmitter.onTimeout(() -> NotificationController.'Map이름'.remove(userId));
        // sseEmitter.onError((e) -> NotificationController.'Map이름'.remove(userId));
        return ResponseEntity.ok(emitter);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<SseEmitter> getAnswersDetail(@PathVariable("questionId") Long questionId){

        SseEmitter emitter = new SseEmitter();
        sseEmitters.add(emitter);

        try {
            QuestionGroup questionGroup = new QuestionGroup();
            // int questionGroupNum = questionGroup.getLastWeekGroupNum();

            //voteTable 갱신 필요
            answerService.iniAnswers();

            List<AnswerDetailResponse>[] result = answerService.selectDetailAnswer(questionId);

            emitter.send(SseEmitter.event().name("detailAnswer").data(result));
        }catch (IOException e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(emitter);
    }
}
