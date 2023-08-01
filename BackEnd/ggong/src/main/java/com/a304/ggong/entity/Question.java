package com.a304.ggong.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "question")
@Getter
@Setter
public class Question {

	@Id
	@GeneratedValue
	@Column(name = "question_ID")
	private Long questionID;

	@Column(name = "content")
	private String content;

	@Column(name = "group")
	private int group;

	@Column(name = "category")
	private String category;

	@Column(name = "optionA")
	private String optionA; // A에 들어갈 대답 내용

	@Column(name = "optionB")
	private String optionB; // B에 들어갈 대답 내용

	@Enumerated(EnumType.STRING)
	private QuestionType type;

	;;
}
