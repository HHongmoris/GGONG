package com.a304.ggong.entity;

import javax.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

	@OneToMany(mappedBy = "question")
	@Builder.Default
	private List<Vote> votes = new ArrayList<>();
}
