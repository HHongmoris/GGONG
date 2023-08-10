package com.a304.ggong.dto.response;

import java.sql.Timestamp;

import com.a304.ggong.entity.Point;
import com.a304.ggong.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointListResponse {
    private Timestamp eventTime;
    private int balancePoint;
    private int point;
    private String machineName;
    private int price;

    public PointListResponse(Point entity){
        this.eventTime = entity.getEventTime();
        this.balancePoint = entity.getBalancePoint();
        this.point = entity.getPoint();
        this.machineName = entity.getVote().getMachine().getName();
        this.price = entity.getBuy().getProduct().getPrice();
    }
}
