import React, { useState } from 'react';
import { Title } from '../../components/Heading';
import { Border } from '../../global/colors';
import { Tab, category } from '../../components/Tab';
import BarChart from '../../components/Chart/BarChart';

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

const sampleData2 = [
  {
    label: '공연',
    value: 90,
    ratio: 90,
  },
  {
    label: '주점',
    value: 10,
    ratio: 10,
  },
];

const sampleData3 = [
  {
    label: '야근',
    value: 55,
    ratio: 55,
  },
  {
    label: '주말 출근',
    value: 45,
    ratio: 45,
  },
];

// 페이지 num => 어떤 카테고리를 쓸지 0이면 ['공통', '대학', '기업'], 2면 ['지역별', '연령별', '기업별']
const AnswerPage = ({ num = 0 }) => {
  // 탭 활성화 관리 (처음 상태 활성화 탭은 첫번째 탭)
  const [activeTab, setActiveTab] = useState(0);

  // 클릭했을 때 tabIndex에 해당하는 탭이 활성화
  const handleTabClick = tabIndex => {
    setActiveTab(tabIndex);
  };

  return (
    <div>
      <Title content="진행 중인 투표" />
      {/* category, activeTab, onClick 이벤트 */}
      <Tab category={category[num]} activeTab={activeTab} onClick={handleTabClick} />
      {activeTab === 0 && (
        <div className={`card border ${Border.MAIN} mt-4`}>
          <div className="p-4">
            <BarChart title="당신이 좋아하는 술은?" data={sampleData1} />
          </div>
        </div>
      )}
      {activeTab === 0 && (
        <div className={`card border ${Border.MAIN} mt-4`}>
          <div className="p-4">
            <BarChart title="당신이 좋아하는 술은?" data={sampleData1} />
          </div>
        </div>
      )}
      {activeTab === 0 && (
        <div className={`card border ${Border.MAIN} mt-4`}>
          <div className="p-4">
            <BarChart title="당신이 좋아하는 술은?" data={sampleData1} />
          </div>
        </div>
      )}
      {activeTab === 1 && (
        <div className={`card border ${Border.MAIN} mt-4`}>
          <div className="p-4">
            <BarChart title="축제 때 더 기대되는 것은?" data={sampleData2} />
          </div>
        </div>
      )}
      {activeTab === 1 && (
        <div className={`card border ${Border.MAIN} mt-4`}>
          <div className="p-4">
            <BarChart title="축제 때 더 기대되는 것은?" data={sampleData2} />
          </div>
        </div>
      )}
      {activeTab === 1 && (
        <div className={`card border ${Border.MAIN} mt-4`}>
          <div className="p-4">
            <BarChart title="축제 때 더 기대되는 것은?" data={sampleData2} />
          </div>
        </div>
      )}
      {activeTab === 2 && (
        <div className={`card border ${Border.MAIN} mt-4`}>
          <div className="p-4">
            <BarChart title="더 싫은 것은?" data={sampleData3} />
          </div>
        </div>
      )}
      {activeTab === 2 && (
        <div className={`card border ${Border.MAIN} mt-4`}>
          <div className="p-4">
            <BarChart title="더 싫은 것은?" data={sampleData3} />
          </div>
        </div>
      )}
      {activeTab === 2 && (
        <div className={`card border ${Border.MAIN} mt-4`}>
          <div className="p-4">
            <BarChart title="더 싫은 것은?" data={sampleData3} />
          </div>
        </div>
      )}
    </div>
  );
};

export default AnswerPage;
