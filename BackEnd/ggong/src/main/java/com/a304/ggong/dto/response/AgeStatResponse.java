package com.a304.ggong.dto.response;

import com.a304.ggong.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgeStatResponse {
    private String ageRange;
    private Long ageRangeCnt;

    public AgeStatResponse(User entity){
        this.ageRange = entity.getAgeRange();
    }
}
