package com.a304.ggong.dto.response;

import java.sql.Timestamp;

import com.a304.ggong.entity.Point;
import com.a304.ggong.entity.Product;
import lombok.*;

import javax.persistence.Column;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PointListResponse {
    private Timestamp eventTime;
    private Integer balancePoint;
    private int point;
    // 이거 상황에 따라 null 될수있도록 처리 해줘야겠네...
    // null 값들 다 빼니까 잘나와
    @Column(nullable = true)
    private String machineName;

    @Column(nullable = true)
    private int price;

    public PointListResponse(Point entity){
        this.eventTime = entity.getEventTime();
        this.balancePoint = entity.getBalancePoint();
        this.point = entity.getPoint();
       this.machineName = entity.getVote().getMachine().getName();
       this.price = entity.getBuy().getProduct().getPrice();
    }
}
