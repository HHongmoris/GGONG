import React from 'react';

import PointList from '../../components/List/PointList';

// 포인트 내역 페이지
const PointHistoryPage = ({ data = [], search }) => {
  return (
    <div>
      <PointList data={data} search={search} />
    </div>
  );
};

export default PointHistoryPage;
