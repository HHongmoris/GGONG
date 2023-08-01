import React from 'react';

// 필요한 카테고리 데이터
const category = [
  ['공통', '대학', '기업'],
  ['지역별', '연령별', '대학별'],
  ['지역별', '연령별', '기업별'],
  ['지역별', '연령별'],
];

// 탭에 쓰일 카테고리, 활성화탭 여부, 클릭이벤트
const Tab = ({ category, activeTab, onClick }) => {
  return (
    <div className="tabs tabs-boxed">
      {/* 카테고리 수만큼 탭 생성 */}
      {category.map((categoryName, idx) => {
        return (
          <button
            key={idx}
            // activeTab인지 여부 판단
            className={`${activeTab === idx ? 'btn bg-yellow-400 text-white' : 'btn'}`}
            onClick={() => onClick(idx)}
          >
            {categoryName}
          </button>
        );
      })}
    </div>
  );
};

export { category, Tab };
