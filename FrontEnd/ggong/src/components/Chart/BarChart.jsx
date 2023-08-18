import React from 'react';

import { Text, Color } from '../../global/colors';

import { Subtitle } from '../Heading';

/**
 * 전달받은 데이터를 이용해서 막대 차트를 만들어주는 컴포넌트
 *
 * @param {string} title 차트의 제목
 * @param {Data[]} data 차트에 사용될 데이터
 * @returns 데이터를 토대로 만든 막대 차트
 */
const BarChart = ({ title, data }) => {
  // 데이터를 구조분해할당 하여 변수 A, B에 저장합니다
  const [A, B] = data;
  const textColor = Text['WHITE'];

  return (
    <div>
      {/* 차트 제목 */}
      <Subtitle content={title} spacing={true} />
      <div className={`flex ${textColor} space-x-[-1px] text-center`}>
        {/* 답변 A에 해당하는 데이터가 표시되는 영역 */}
        <div
          style={{ width: A.ratio + '%' }}
          className={'flex justify-between items-center min-w-fit bg-blue-400 h-10 px-2 space-x-1'}
        >
          <div>{A.label?.length > 5 ? A.label.substring(0, 4) + '...' : A.label}</div>
          <div>{A.value}</div>
        </div>
        {/* 답변 A, B를 나누는 영역이며 중간에 VS 문구가 있음 */}
        <div className={'flex items-center z-10 bg-gradient-to-r from-blue-400 to-red-400 h-10 text-xl'}>VS</div>
        {/* 답변 B에 해당하는 데이터가 표시되는 영역 */}
        <div
          style={{ width: B.ratio + '%' }}
          className={'flex justify-between items-center min-w-fit bg-red-400 h-10 px-2 space-x-1'}
        >
          <div>{B.value}</div>
          <div>{B.label?.length > 5 ? B.label.substring(0, 4) + '...' : B.label}</div>
        </div>
      </div>
    </div>
  );
};

export default BarChart;
