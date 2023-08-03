package com.a304.ggong.dto.response;

import com.a304.ggong.entity.Buy;
import com.a304.ggong.entity.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyListResponse {
    private int price;
    private String pin;

    public BuyListResponse(Buy entity){
        this.price = entity.getProductNo().getPrice();
        this.pin = entity.getProductNo().getPin();
    }
}
