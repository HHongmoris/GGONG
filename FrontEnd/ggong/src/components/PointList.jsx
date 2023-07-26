import React from 'react';

import { Text } from '../global/colors';
import PointListItem from './PointListItem';

const PointList = ({ data = [] }) => {
  return (
    <div>
      <h1 className="text-3xl font-bold">포인트 내역</h1>
      <div className="divider"></div>
      <span>기간 설정</span>
      <div className="flex justify-between">
        <div>
          <input type="date" className={`${Text.GRAY}`} />
        </div>
        <span> ~ </span>
        <div>
          <input type="date" className={`${Text.GRAY}`} />
        </div>
      </div>
      <div className="divider"></div>
      {data.map((datum, idx) => {
        const { date, detail, variant, balance } = datum;
        return <PointListItem key={idx} date={date} detail={detail} variant={variant} balance={balance} />;
      })}
    </div>
  );
};

export default PointList;
