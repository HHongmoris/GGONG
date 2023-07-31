import React from 'react';

import { Text } from '../../global/colors';
import Coupon from './Coupon';

/**
 * 포인트 내역 리스트에서 한 내역을 표시하기 위한 컴포넌트
 *
 * @param {number} variant 포인트의 변동량을 담고 있는 정수
 * @param {number} balance 잔여 포인트를 담고 있는 정수
 * @returns 전달받은 값들로 구성된 하나의 내역 컴포넌트를 반환
 */
const InventoryListItem = ({ price = 0, PIN = 'AAAA-AAAA-AAAA-AAAA' }) => {
  const priceSep = price.toLocaleString('ko-KR');

  return (
    <div>
      <div className="flex justify-between mb-8 mt-8">
        <Coupon price={price} />
        <div className="flex flex-col justify-center">
          <span className="text-sm font-bold">PIN 번호 </span>
          <div className="flex justify-between items-center">
            <span className="text-sm">{PIN} </span>
          </div>
        </div>
      </div>
      <div className="divider"></div>
    </div>
  );
};

export default InventoryListItem;
