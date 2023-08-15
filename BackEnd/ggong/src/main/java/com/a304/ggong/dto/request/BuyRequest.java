package com.a304.ggong.dto.request;

import com.a304.ggong.entity.Buy;
import com.a304.ggong.entity.Product;
import com.a304.ggong.entity.User;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BuyRequest {
//    private Long productNo;

    private Product product;
    private User user;
    private Timestamp buyTime;

    public Buy toEntity(){
        return Buy.builder().buyTime(buyTime).user(user).product(product).build();
    }
}
