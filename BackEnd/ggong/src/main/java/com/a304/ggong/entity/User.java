package com.a304.ggong.entity;

import javax.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_no")
	private Long userNo;

	@Column(name = "nickname")
	private String name;

	@Column(name = "age_range")
	private String ageRange;

	@Column(name = "gender")
	private String gender;

	@Column(name = "email")
	private String email;

	@Column(name = "favorite_cigarette")
	private String favoriteCigarette;

	@Column(name = "QR")
	private String QR;

	@Column(name = "user_rating")
	private String userRating;

	@OneToMany(mappedBy = "user")
	@Builder.Default
	private List<User> users = new ArrayList<>();

}
