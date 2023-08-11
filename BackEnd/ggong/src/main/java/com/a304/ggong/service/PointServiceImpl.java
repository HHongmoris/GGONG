package com.a304.ggong.service;

import com.a304.ggong.dto.response.PointListResponse;
import com.a304.ggong.entity.Point;
import com.a304.ggong.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService{
    private final PointRepository pointRepository;

    //사용자 이메일로 포인트 리스트 찾기
    @Override
    public List<PointListResponse> selectPointListByUserEmail(String userEmail) {
        return pointRepository.findAllByUserEmail(userEmail)
                .stream()
                .map(PointListResponse::new)
                .collect(Collectors.toList());
    }

    //잔여 포인트 계산
    @Override
    public int calculateBalancePoint(String theDate) {
        Timestamp time = Timestamp.valueOf(theDate);
        int balancePoint = pointRepository.selectBalancePoint(time);
        return balancePoint;
    }

    //특정 기간의 포인트 내역 조회
    @Override
    public List<PointListResponse> selectPointListByEventTime(String startDate, String endDate) {
        return pointRepository.findByEventTimeBetween(startDate, endDate)
                .stream()
                .map(PointListResponse::new)
                .collect(Collectors.toList());
    }
}
