package com.a304.ggong.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserSignUpDto {

    private String name;
    private String password;
    private String ageRange;
    private String gender;
    private String email;
    private String favoriteCigarette;
    private String QR;
    private String userRating;
}
