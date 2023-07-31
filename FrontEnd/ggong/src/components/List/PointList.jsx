import React from 'react';

import { Text } from '../../global/colors';
import { Title } from '../Heading';
import PointListItem from './PointListItem';

/**
 *
 * @param {Array} data 포인트 내역의 배열
 * @returns data를 토대로 포인트 내역 리스트 컴포넌트를 만들어서 반환
 */
const PointList = ({ data = [] }) => {
  return (
    <div>
      <Title content="포인트 내역" />
      <div className="divider"></div>
      <span>기간 설정</span>
      {/* 내역을 보고 싶은 기간의 시작일 */}
      <div className="flex justify-between">
        <div>
          <input type="date" className={`${Text.GRAY}`} />
        </div>
        <span>~</span>
        {/* 내역을 보고 싶은 기간의 종료일 */}
        <div>
          <input type="date" className={`${Text.GRAY}`} />
        </div>
      </div>
      <div className="divider"></div>
      {/* 각 내역을 PointListItem 형식으로 변환해서 표시 */}
      {data.map((datum, idx) => {
        const { datetime, detail, variant, balance } = datum;
        return <PointListItem key={idx} datetime={datetime} detail={detail} variant={variant} balance={balance} />;
      })}
    </div>
  );
};

export default PointList;
