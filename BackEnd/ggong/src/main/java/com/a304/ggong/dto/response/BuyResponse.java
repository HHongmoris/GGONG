package com.a304.ggong.dto.response;

import com.a304.ggong.entity.Buy;
import com.a304.ggong.entity.Product;
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
    private boolean bought;
    private Long productNo;

    public BuyResponse(Product entity){
        this.productNo = entity.getProductNo();
    }
}
