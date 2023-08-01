package com.a304.ggong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionDto {

	private Long questionID;
	private String content;
	private int group;
	private String category;
	private String optionA;
	private String optionB;
	private String type; // enum
}
