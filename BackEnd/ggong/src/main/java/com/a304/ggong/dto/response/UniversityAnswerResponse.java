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
public class UniversityAnswerResponse {

	private String universityName; // Service에서 쿼리문으로 대학명 추출해 넣어주기
	private String optionA;
	private String optionB;

	// Service에서 연산해서 넣어주기
	private String answerA;
	private String answerB;

	public UniversityAnswerResponse(Question entityQ) {
		this.optionA = entityQ.getOptionA();
		this.optionB = entityQ.getOptionB();
	}
}
