package com.a304.ggong.dto.request;

import com.a304.ggong.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyRequest {
    private Long productNo;

    public Product toEntity(){
        return Product.builder()
                .productNo(productNo)
                .build();
    }
}
