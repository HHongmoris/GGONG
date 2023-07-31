package com.a304.ggong.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "machine")
@Getter
@Setter
public class Vote {

	@Id
	@GeneratedValue
	@Column(name = "vote_no")
	private int voteNo;

	// user_no, machine_no, question_ID는 어노테이션으로 조인해줘야해서...
	// 머지 후에 하겠습니당

	@Column(name = "vote_date")
	private Timestamp voteDate;

	@Column(name = "answer")
	private int answer;
}
