package com.a304.ggong.dto.response;

import com.a304.ggong.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AgeStatResponse {
    private String ageRange;
    private Long ageRangeCnt;

    public AgeStatResponse(User entity){
        this.ageRange = entity.getAgeRange();
    }
}
