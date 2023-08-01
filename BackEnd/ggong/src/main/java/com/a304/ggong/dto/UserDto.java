package com.a304.ggong.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {

    private Long user_no;
    private String name;
    private String age_range;
    private String gender;
    private String email;
    private String favorite_cigarette;
    private String QR;
    private String user_rating;
}
