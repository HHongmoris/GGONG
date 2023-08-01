package com.a304.ggong.dto.response;

import com.a304.ggong.entity.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllItemResponse {
    private long productNo;
    private int price;

//    //얘는 리스폰스니까 이거 없어야함
//    public Product toEntity(){
//        return Product.builder()
//                .productNo(productNo)
//                .price(price)
//                .build();
//    }
}
