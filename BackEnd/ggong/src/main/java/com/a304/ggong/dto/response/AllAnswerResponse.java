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
public class AllAnswerResponse {

	private Long questionID;
	private String content;
	private String optionA;
	private String optionB;
	// Service에서 넣어주기
	// 투표 수
	private Long answerA;
	private Long answerB;
	// 비율
	private double rateA;
	private double rateB;

	public AllAnswerResponse(Question entity) {
		this.questionID = entity.getQuestionID();
		this.content = entity.getContent();
		this.optionA = entity.getOptionA();
		this.optionB = entity.getOptionB();
	}
}
