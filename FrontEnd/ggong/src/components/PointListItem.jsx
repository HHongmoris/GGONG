import React from 'react';
import { Text } from '../global/colors';

/**
 * 포인트 내역 리스트에서 한 내역을 표시하기 위한 컴포넌트
 *
 * @param {string} datetime 날짜 정보를 담고 있는 연도월일 시:분 형태의 문자열
 * @param {string} detail 포인트 변동 내역을 담고 있는 문자열
 * @param {number} variant 포인트의 변동량을 담고 있는 정수
 * @param {number} balance 잔여 포인트를 담고 있는 정수
 * @returns 전달받은 값들로 구성된 하나의 내역 컴포넌트를 반환
 */
const PointListItem = ({ datetime = 'yyyy-MM-dd(ddd) HH:MM', detail = '사용 내역', variant = 0, balance = 0 }) => {
  // 숫자에 3자리 단위마다 콤마를 표시하고, 변동량이 양수라면 앞에 +를 붙입니다.
  variant = (variant > 0 ? '+' : '') + variant.toLocaleString('ko-KR');
  // 숫자에 3자리 단위마다 콤마를 표시합니다.
  balance = balance.toLocaleString('ko-KR');

  // '연도월일 시:분' 문자열을 [연도월일, 시:분] 문자열로 분리합니다.
  const [date, time] = datetime.split(' ');

  return (
    <div>
      <div className="flex justify-between mb-1">
        <div className={`text-sm ${Text.GRAY} space-x-2`}>
          <span>{date}</span>
          <span>{time}</span>
        </div>
        {/* 변동량이 양수이면 빨강, 0을포함한 음수이면 파랑색으로 표시합니다. */}
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
