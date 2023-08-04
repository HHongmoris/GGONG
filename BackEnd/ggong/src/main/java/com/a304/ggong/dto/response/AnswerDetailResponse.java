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
public class AnswerDetailResponse {

	// 분류별 이름
	private String content;
	private String optionA;
	private String optionB;

	// service
	private String dataLabel; // 지역명 or 대학명 or 기업명 or 연령
	// repo에 쿼리 박아놓고...
	// service에서 처리 후에 넣어주기
	private Long answerA;
	private Long answerB;
	private double rateA;
	private double rateB;

	public AnswerDetailResponse(Question entity) {
		this.content = entity.getContent();
		this.optionA = entity.getOptionA();
		this.optionB = entity.getOptionB();
	}
}
