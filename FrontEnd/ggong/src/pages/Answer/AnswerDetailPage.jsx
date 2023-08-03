import React, { useState } from 'react';

import { Border } from '../../global/colors';
import BarChart from '../../components/Chart/BarChart';
import { Tab, category } from '../../components/Tab';

// 샘플 데이터들... 나중에 데이터 받으면 없앱시다.
const sampleData1 = [
  {
    label: '소주',
    value: 30,
    ratio: 30,
  },
  {
    label: '맥주',
    value: 70,
    ratio: 70,
  },
];

// 질문에 대한 응답 상세 페이지
const AnswerDetailPage = ({ num = 1 }) => {
  // 탭 활성화 관리 (처음 상태 활성화 탭은 첫번째 탭)
  const [activeTab, setActiveTab] = useState(0);

  // 클릭했을 때 tabIndex에 해당하는 탭이 활성화
  const handleTabClick = tabIndex => {
    setActiveTab(tabIndex);
  };

  return (
    <div>
      <div className={`card border ${Border.MAIN} mt-4`}>
        <div className="p-4">
          <BarChart title="당신이 좋아하는 술은?" data={sampleData1} />
        </div>
      </div>
      <Tab category={category[num]} activeTab={activeTab} onClick={handleTabClick} />
      {activeTab === 0 && <BarChart title="대장님 집" data={sampleData1} />}
      {activeTab === 0 && <BarChart title="홍박사 집" data={sampleData1} />}
      {activeTab === 1 && <BarChart title="20대" data={sampleData1} />}
      {activeTab === 1 && <BarChart title="30대" data={sampleData1} />}
      {activeTab === 2 && <BarChart title="하버드" data={sampleData1} />}
      {activeTab === 2 && <BarChart title="예일" data={sampleData1} />}
    </div>
  );
};

export default AnswerDetailPage;
