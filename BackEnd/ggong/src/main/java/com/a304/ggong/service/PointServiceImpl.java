package com.a304.ggong.service;

import com.a304.ggong.dto.response.PointListResponse;
import com.a304.ggong.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService{
    private final PointRepository pointRepository;

    //선택한 기간에 따른 포인트 내역 조회
    @Override
    public List<PointListResponse> selectPointList(String start, String end) {
        // 기간 설정
        // 사용 정보에 따른 machineName/price 표기
        // 잔여 포인트 계산
        return null;
    }
}
