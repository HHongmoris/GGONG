import React from 'react';
import MachineCard from '../Card/MachineCard';

/**
 * 모달창의 내용 컴포넌트를 반환하는 함수
 *
 * @param {boolean} open 현재 컴포넌트가 보이는지 여부
 * @param {Function} toggleVisible open의 상태를 변경하는 함수
 * @returns 모달창 내용 컴포넌트
 */
const MarkerContent = ({ open, toggleVisible }) => {
  return (
    <div>
      {open && (
        <div className="absolute left-0 top-0 w-full h-full flex justify-center items-center">
          {/* 모달 배경의 그림자 영역, 클릭 시 모달이 닫힘*/}
          <div
            className="absolute left-0 top-0 w-full h-full opacity-50 bg-gray-400 z-20"
            onClick={toggleVisible}
          ></div>
          {/* 모달 내용 (기기 상세 창) */}
          <div className="w-80 z-20">
            <MachineCard />
          </div>
        </div>
      )}
    </div>
  );
};

export default MarkerContent;
