import React, { useState } from 'react';

// 필요한 카테고리 데이터
const category = [
  ['공통', '대학', '기업'],
  ['지역별', '연령별', '대학별'],
  ['지역별', '연령별', '기업별'],
  ['지역별', '연령별'],
];

// 카테고리 데이터와 num을 받는다. (num은 카테고리 데이터 중 몇 번째 배열을 쓸 것인지 결정)
const Tab = ({ num = 0, data = category }) => {
  // 탭의 활성화 상태를 관리하는 state 설정 (첫번째 탭을 기본으로 설정)
  const [activeTab, setActiveTab] = useState(1);

  // 탭을 클릭했을 때 활성화 상태를 업데이트하는 함수
  // tabIndex번 클릭했다고 출력
  const handleTabClick = tabIndex => {
    setActiveTab(tabIndex);
    console.log(`${tabIndex}번 버튼 클릭`);
  };

  return (
    <div className="tabs tabs-boxed">
      {data[num].map((categoryName, idx) => {
        // data[num]에 있는 데이터들을 순회
        return (
          <button
            key={idx} // 각 버튼의 고유한 key 값
            className={`${activeTab === idx ? 'btn bg-yellow-400 text-white' : 'btn'}`} // 활성화되면 버튼 스타일 바꿔줌
            // 클릭하면 setActiveTab(idx) 이벤트 발생 (activeTab 활성화)
            onClick={() => handleTabClick(idx)}
          >
            {categoryName}
          </button>
        );
      })}
    </div>
  );
};

export default Tab;
