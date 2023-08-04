package com.a304.ggong.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenderStatResponse {
    private Long male;
    private Long female;
    
    //성별에 따른 사용자 수 카운트 해야함
}
