package com.a304.ggong.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "favoritemachine")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FavoriteMachine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "favoritemachine_no")
	private Long favoriteMachineNo;

	// user_no
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_no")
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
