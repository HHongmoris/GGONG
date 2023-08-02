package com.a304.ggong.dto.response;

import com.a304.ggong.entity.Point;
import com.a304.ggong.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointListResponse {
    private String eventTime;
    private int balancePoint;
    private int point;
    private String machineName;
    private int price;

    public PointListResponse(Point entity){
        this.eventTime = entity.getEventTime();
        this.balancePoint = entity.getBalancePoint();
        this.point = entity.getPoint();
        this.machineName = entity.getVoteNo().getMachine().getName();
        this.price = entity.getBuyNo().getProductNo().getPrice();
    }
}
