package com.a304.ggong.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyResponse {
    //샀는지 안샀는지 응답 코드만 보내기
    private boolean bought = true;
}
