package com.a304.ggong.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "machine")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote {

	@Id
	@GeneratedValue
	@Column(name = "vote_no")
	private Long voteNo;

	// user_no
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_no")
	private User user;

	// machine_no
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "machine_no")
	private Machine machine;

	// question_ID
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_ID")
	private Question question;

	@Column(name = "vote_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date voteDate;

	@Column(name = "answer")
	private int answer;
}
