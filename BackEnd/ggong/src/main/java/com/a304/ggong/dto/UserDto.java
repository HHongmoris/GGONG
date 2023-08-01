package com.a304.ggong.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {

    private Long userNo;
    private String name;
    private String ageRange;
    private String gender;
    private String email;
    private String favoriteCigarette;
    private String QR;
    private String userRating;
}
