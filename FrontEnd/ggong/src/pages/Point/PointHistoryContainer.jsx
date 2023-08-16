import React, { useEffect, useState } from 'react';

import { useLocation } from 'react-router-dom';
import { useSelector } from 'react-redux';

import PointHistoryPage from './PointHistoryPage';
import useApi from '../../hooks/useApi';

// 포인트 내역 페이지에 필요한 데이터를 관리하는 컨테이너
const PointContainer = () => {
  // 포인트 내역 데이터
  const [points, setPoints] = useState([]);
  const user = useSelector(state => state.user);

  // location 객체 생성 (현재 URL 정보 가져오기)
  const location = useLocation();

  const search = (start, end) => {
    if (!start || !end) {
      useApi('/points', 'GET', user.token).then(res => {
        const histories = [];
        res.data.forEach(datum => {
          const { eventTime, balancePoint, point, machineName, price } = datum;
          const time = eventTime.split('T');

          const detail = machineName ? `${machineName}점 에서 투표` : `${price}원 상품권 구매`;
          const history = {
            eventTime: `${time[0]} ${time[1].slice(0, 5)}`,
            balance: balancePoint,
            variant: point,
            detail: detail,
          };
          histories.push(history);
        });
        setPoints(histories);
      });
    } else {
      useApi(`/points/point-list?start=${start}&end=${end}`, 'GET', user.token).then(res => {
        const histories = [];
        res.data.forEach(datum => {
          const { eventTime, balancePoint, point, machineName, price } = datum;
          const time = eventTime.split('T');

          const detail = machineName ? `${machineName}점 에서 투표` : `${price}원 상품권 구매`;
          const history = {
            eventTime: `${time[0]} ${time[1].slice(0, 5)}`,
            balance: balancePoint,
            variant: point,
            detail: detail,
          };
          histories.push(history);
        });
        setPoints(histories);
      });
    }
  };

  useEffect(() => {
    search();
  }, [location]);

  return (
    <div>
      <PointHistoryPage data={points} search={search} />
    </div>
  );
};

export default PointContainer;
