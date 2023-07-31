package com.a304.ggong.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	// user_no
	@ManyToOne
	@JoinColumn(name = "user_no")
	private User user;

	// machine_no
	@ManyToOne
	@JoinColumn(name = "machine_no")
	private Machine machine;

	// question_ID
	@ManyToOne
	@JoinColumn(name = "question_ID")
	private Question question;

	@Column(name = "vote_date")
	private Timestamp voteDate;

	@Column(name = "answer")
	private int answer;
}
