package com.a304.ggong.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "favoritemachine")
@Getter
@Setter
@NoArgsConstructor
public class FavoriteMachine {

	@Id
	@GeneratedValue
	@Column(name = "favoritemachine_no")
	private Long favoriteMachineNo;

	// user_no
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "email")
	private User user;

	// machine_no
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "machine_no")
	private Machine machine;

	@Builder
	public FavoriteMachine(User user, Machine machine) {
		this.user = user;
		this.machine = machine;
	}
}
