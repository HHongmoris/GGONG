package com.a304.ggong.service;

import com.a304.ggong.dto.response.PointListResponse;
import com.a304.ggong.entity.Point;
import com.a304.ggong.entity.User;
import com.a304.ggong.repository.PointRepository;
import com.a304.ggong.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService{
    private final PointRepository pointRepository;
    private final UserRepository userRepository;

    // point 첫 화면
    @Override
    public List<PointListResponse> selectPointAll(String email) {
        User user = userRepository.findByEmail(email).get();
        List<PointListResponse> list = new ArrayList<>();

        List<Point> pointList = pointRepository.findAllByUser_UserNo(user.getUserNo());

        // 이부분이 문제래...
        for(int idx = 0; idx< pointList.size(); idx++){
            //이거 출력 잘 됨
//            System.out.println("이것은 tmp:" +new PointListResponse(pointList.get(idx)));
            // 여기서 계속 잔여포인트 때문에 null exception이 뜬다.
            // 그래서 임시방편으로 balancePoint를 Integer 값으로 주었다.
            // vote테이블이 올라가면서 point테이블이 올라가고 그다음 그 메소드 내에서 바로 잔여포인트를 구해주는 로직이 필요하다고 판단.
            // price도 마찬가지! -> int는 null값이 안됨. 그래서 Integer로 대체
            PointListResponse tmp = new PointListResponse(pointList.get(idx));
            list.add(tmp);
        }

        return list;
    }

    //사용자 이메일로 포인트 리스트 찾기
    @Override
    public List<PointListResponse> selectPointListByUserEmailAndDate(String userEmail, String start, String end) {
        // email 이용해서 userNo 구하기
        User user = userRepository.findByEmail(userEmail).get();

        start += " 00:00:00.0";
        end += " 23:59:59.9";

        Timestamp startDate = Timestamp.valueOf(start);
        Timestamp endDate = Timestamp.valueOf(end);

        return pointRepository.findByUserNoAndEventTimeBetween(user.getUserNo(),startDate,endDate)
                .stream()
                .map(PointListResponse::new)
                .collect(Collectors.toList());
    }

    //잔여 포인트 계산
    @Override
    public Integer calculateBalancePoint(String email, Timestamp end) throws NullPointerException {
        // 여기서 현재 날짜 추출해서
        // point entity에 넣어주기(데이터 update)

        // 현재 날짜 설정
//        end += " 23:59:59.9";
        LocalDateTime now = LocalDateTime.now();
        Timestamp Tnow = Timestamp.valueOf(now);

        // email로 userNo 찾기
        User user = userRepository.findByEmail(email).get();

        Integer balancePoint = pointRepository.selectBalancePoint(Tnow,user.getUserNo());

        // 마지막 point entity는 어떻게 찾을까...
        // userNo가 같은 데이터들 중, 내림차순 해서 limit1걸어주자!
       // Point point = pointRepository.findTopByUser_UserNoOOrderByEventTimeDesc(user.getUserNo());

        List<Point> list = pointRepository.findAllByUser_UserNo(user.getUserNo());

        if(list.size()==0){
            return null;
        }
        Point point = list.get(list.size()-1);

//        Point point = pointRepository.findTopByUser_UserNoOOrderByEventTimeDesc(user.getUserNo());
        point.setBalancePoint(balancePoint);

        return balancePoint;
    }

    //특정 기간의 포인트 내역 조회
//    @Override
//    public List<PointListResponse> selectPointListByEventTime(String startDate, String endDate) {
//        return pointRepository.findByEventTimeBetween(startDate, endDate)
//                .stream()
//                .map(PointListResponse::new)
//                .collect(Collectors.toList());
//    }
}
