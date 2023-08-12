package com.a304.ggong.dto.response;

import com.a304.ggong.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAndAnswerCnt {
    //투표를 보여주는 페이지에서 그래프를 만들기 위해 그걸 담을 클래스 생성
    // 투표 번호에 따른 결과 카운트 값 담을 예정
    private Long questionID;
    private Long answerCnt;

    public QuestionAndAnswerCnt(Question entity) {
        this.questionID = entity.getQuestionID();
    }
}
