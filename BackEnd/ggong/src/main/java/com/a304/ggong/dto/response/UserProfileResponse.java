package com.a304.ggong.dto.response;

import com.a304.ggong.entity.Point;
import com.a304.ggong.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {
	private Long userNo; // 회원 번호
	private String email; // 회원 이메일
	private String nickName; // 유저 닉네임
	private String userRating; // 유저 등급
	private String ageRange; // 유저 연령대
	private String gender; // 유저 성별
	private String favoriteCigarette; // 유저 선호 담배
	private String QR; // 유저 큐알
	private int points; // 유저 포인트

	public UserProfileResponse(User entityU, Point entityP) {
		this.userNo = entityU.getUserNo();
		this.email = entityU.getEmail();
		this.nickName = entityU.getName();
		this.userRating = entityU.getUserRating();
		this.ageRange = entityU.getAgeRange();
		this.gender = entityU.getGender();
		this.favoriteCigarette = entityU.getFavoriteCigarette();
		this.QR = entityU.getQR();
	}
}
