package com.a304.ggong.service;

import com.a304.ggong.dto.response.PointListResponse;

import java.sql.Timestamp;
import java.util.List;

public interface PointService {

    List<PointListResponse> selectPointAll(String email);

    //포인트 내역 조회(기간에 따른 조회, 잔여포인트)
    List<PointListResponse> selectPointListByUserEmailAndDate(String userEmail,String startDate, String endDate);

    //잔여포인트 계산
//    Integer calculateBalancePoint(String email, String end);
    Integer calculateBalancePoint(String email, Timestamp end);

//    List<PointListResponse> selectPointListByEventTime(String startDate, String endDate);



}
