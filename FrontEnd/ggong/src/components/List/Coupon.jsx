import React from 'react';

import { Background, Text } from '../../global/colors';

/**
 * 전달받은 가격의 쿠폰 아이콘을 반환하는 컴포넌트
 *
 * @param {number} price
 * @returns price원 쿠폰 아이콘을 표시하는 컴포넌트
 */
const Coupon = ({ price = 0 }) => {
  return (
    <div className="relative w-48 z-0">
      {/* 쿠폰 우측 펀칭 모양을 표현하는 요소 */}
      <div className={`absolute left-44 top-8 w-8 h-8 rounded-full ${Background.WHITE}`}></div>
      {/* 쿠폰을 표현하는 요소 */}
      <div className={`content-center w-48 h-24 ${Background.MAIN}`}>
        <div className="flex justify-between pl-5 pr-1 w-44 divide-x-2 divide-dotted divide-zinc-600">
          <div className="flex flex-col justify-center">
            {/* 쿠폰의 내용을 나타내는 영역 */}
            <span className={`text-xs ${Text.MAIN}`}>문화상품권</span>
            <span className="text-3xl">{price.toLocaleString('ko-KR')} 원</span>
          </div>
          {/* 절취선을 표현하는 요소 */}
          <div className="w-2 h-24"></div>
        </div>
      </div>
    </div>
  );
};

export default Coupon;
