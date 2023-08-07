package com.a304.ggong.entity;

import javax.persistence.*;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@AllArgsConstructor
@Builder
@Table(name = "user")
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

	//추가
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Enumerated(EnumType.STRING)
	private SocialType socialType;

	private String socialId;

	private String refreshToken;

	public void passwordEncode(PasswordEncoder passwordEncoder){
		this.password = passwordEncoder.encode(this.password);
	}

	public void updateRefreshToken(String updateRefreshToken) {
		this.refreshToken = updateRefreshToken;
	}

//	@OneToMany(mappedBy = "user")
//	@Builder.Default
//	private List<User> users = new ArrayList<>();

}
