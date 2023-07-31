package com.a304.ggong.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "favoritemachine")
@Getter
@Setter
public class FavoriteMachine {

	// user_no
	@Id
	@ManyToOne
	@JoinColumn(name = "user_no")
	private User user;

	// machine_no
	@Id
	@ManyToOne
	@JoinColumn(name = "machine_no")
	private Machine machine;
}
