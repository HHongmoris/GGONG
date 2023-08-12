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

	private String dataLabel;
	private Long answerA = 0L;
	private Long answerB = 0L;
	private Long rateA;
	private Long rateB;

}
