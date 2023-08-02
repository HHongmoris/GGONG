package com.a304.ggong.dto.response;

import com.a304.ggong.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyListResponse {
    private int price;
    private String pin;

    public BuyListResponse(Product entity){
        this.price = entity.getPrice();
        this.pin = entity.getPin();
    }
}
