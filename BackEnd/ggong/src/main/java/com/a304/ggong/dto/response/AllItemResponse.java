package com.a304.ggong.dto.response;

import com.a304.ggong.entity.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllItemResponse {
    private Long productNo;
    private int price;

    public AllItemResponse(Product entity){
        this.productNo = entity.getProductNo();
        this.price = entity.getPrice();
    }
}
