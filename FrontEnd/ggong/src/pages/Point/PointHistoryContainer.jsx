import React, { useEffect, useState } from 'react';

import { useLocation } from 'react-router-dom';

import PointHistoryPage from './PointHistoryPage';
import useApi from '../../hooks/useApi';

// 포인트 내역 페이지에 필요한 데이터를 관리하는 컨테이너
const PointContainer = () => {
  const [points, setPoints] = useState([]);

  // location 객체 생성 (현재 URL 정보 가져오기)
  const location = useLocation();

  console.log(location);

  useEffect(() => {
    useApi('/points/', 'GET').then(res => {
      setPoints(res.data);
    });
  }, [location]);

  return (
    <div>
      <PointHistoryPage data={points} />
    </div>
  );
};

export default PointContainer;
