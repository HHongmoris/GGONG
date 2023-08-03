import React, { useEffect, useState } from 'react';

import PointHistoryPage from './PointHistoryPage';
import useApi from '../../hooks/useApi';
// 포인트 내역 페이지에 필요한 데이터를 관리하는 컨테이너
const PointContainer = () => {
  const [points, setPoints] = useState([]);

  useEffect(() => {
    useApi('/points/', 'GET').then(res => {
      setPoints(res.data);
    });
  }, []);

  return (
    <div>
      <PointHistoryPage data={points} />
    </div>
  );
};

export default PointContainer;
