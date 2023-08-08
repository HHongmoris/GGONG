import React, { useEffect, useState } from 'react';

import { Text } from '../../global/colors';
import { Title } from '../Heading';
import PointListItem from './PointListItem';
import IconButton from '../Button/IconButton';

import icons from '../../global/icons';

// 할 일 : 서치 아이콘 크기 조절

/**
 *
 * @param {Array} data 포인트 내역의 배열
 * @returns data를 토대로 포인트 내역 리스트 컴포넌트를 만들어서 반환
 */
const PointList = ({ data = [], search }) => {
  // 기간 시작일, 종료일
  const [start, setStart] = useState('2023-08-01');
  const [end, setEnd] = useState('2023-08-08');

  const handleStartInput = (e, newStart) => {
    // 종료일이 시작일보다 느리다면
    if (end < newStart) {
      alert('시작일은 종료일보다 빨라야 합니다!');
      return;
    }

    setStart(newStart);
  };

  const handleClick = () => {
    search(start, end);
  };

  return (
    <div className="mt-12">
      <Title content="포인트 내역" spacing={true} />
      <div className="divider"></div>
      <span>기간 설정</span>
      {/* 내역을 보고 싶은 기간의 시작일 */}
      <div className="flex justify-between">
        <div>
          <input
            type="date"
            value={start}
            className={`${Text.GRAY}`}
            onChange={e => handleStartInput(e, e.target.value)}
          />
        </div>
        <span>~</span>
        {/* 내역을 보고 싶은 기간의 종료일 */}
        <div>
          <input type="date" value={end} className={`${Text.GRAY}`} onChange={e => setEnd(e.target.value)} />
        </div>
        <div>
          <IconButton icon={icons.SEARCH} size={true} onClick={handleClick} />
        </div>
      </div>
      <div className="divider"></div>
      {/* 각 내역을 PointListItem 형식으로 변환해서 표시 */}
      {data.map((datum, idx) => {
        const { eventTime, detail, variant, balance } = datum;
        return <PointListItem key={idx} datetime={eventTime} detail={detail} variant={variant} balance={balance} />;
      })}
    </div>
  );
};

export default PointList;
