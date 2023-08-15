package com.a304.ggong.dto.response;

import com.a304.ggong.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponse {
    private Long userNo;
    private String email;
    private String nickname;
    private String userRating;
    private String ageRange;
    private String gender;
    private String favoriteCigarette;
    private String QR;
    private Integer points;

    public UserResponse(User user, Integer points) {
        this.userNo = user.getUserNo();
        this.email = user.getEmail();
        this.nickname = user.getName();
        this.userRating = user.getUserRating();
        this.ageRange = user.getAgeRange();
        this.gender = user.getGender();
        this.favoriteCigarette = user.getFavoriteCigarette();
        this.QR = user.getQR();
        this.points = points;
    }
}
