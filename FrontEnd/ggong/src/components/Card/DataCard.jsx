import React from 'react';

import { Background, Border } from '../../global/colors';

import { Subtitle } from '../Heading';

// 데이터 카드 컴포넌트 : 제목(어떤 데이터인지), 데이터를 항목으로 갖는다.
const DataCard = ({ title = '제목', data = '데이터' }) => {
  const bgTitle = Background.MAIN;
  const bgContent = Background.WHITE;
  const borderColor = Border.MAIN;
  // title과 data에 전달된 값이 없으면 제목과 데이터가 출력
  return (
    // DOM
    // card라는 클래스에 너비-32 bg-base-100 등의 속성 넣어줌. card-body에서 아이템들 가운데 정렬 및 배경색 노란색으로 변경
    // style에서는 padding-card 속성을 1rem으로 바꿔서 설정 (기본값이 2rem이었음)
    <div className={'card md: w-36 rounded-md'}>
      <div
        className={`card-body items-center md: h-1/5 ${bgTitle} border ${borderColor} rounded-t-xl`}
        style={{ '--padding-card': '1rem' }}
      >
        <span className="text-xl text-center">{title}</span>
      </div>
      <div
        className={`card-body md: h-1/5 items-center ${bgContent} ${borderColor} border rounded-b-xl`}
        style={{ '--padding-card': '1rem' }}
      >
        <Subtitle content={data} />
      </div>
    </div>
  );
};

export default DataCard;
