import React from 'react';
import { Border, Text } from '../global/colors';

const PointListItem = ({ date = 'yyyy-MM-dd(ddd) HH:MM', detail = '사용 내역', variant = 0, balance = 0 }) => {
  variant = (variant > 0 ? '+' : '') + variant.toLocaleString('ko-KR');
  balance = balance.toLocaleString('ko-KR');

  return (
    <div>
      <div className="flex justify-between mb-1">
        <div className={`text-sm ${Text.GRAY}`}>{date}</div>
        <div className={`${variant > 0 ? 'text-red-400' : 'text-blue-400'}`}>{variant} p</div>
      </div>
      <div className="flex justify-between">
        <div className="font-bold">{detail}</div>
        <div className={`text-sm ${Text.GRAY}`}>{balance} p</div>
      </div>
      <div className="divider"></div>
    </div>
  );
};

export default PointListItem;
