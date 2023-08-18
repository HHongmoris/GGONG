import React, { useState, useEffect } from 'react';

import ProfileCard from '../../components/Card/ProfileCard';
import DataCard from '../../components/Card/DataCard';
import MachineCard from '../../components/Card/MachineCard';

// 메인 페이지
const MainPage = ({ user = {}, today = 0, yesterday = 0, machine = {}, options = [], selected, setSelected }) => {
  const { userNo, userRating, nickname, points } = user;

  return (
    <div className="mx-5 pb-5">
      <ProfileCard grade={userRating} nickname={nickname} point={points} qr={userNo} isVisible={true} />
      <div className="flex justify-between my-10">
        {/* 오늘 담배 수 */}
        <DataCard title="오늘 투표수" data={today} />
        {/* 어제 담배 수 */}
        <DataCard title="어제 투표수" data={yesterday} />
      </div>
      {/* 선택 가능한 기기번호 목록, 기기 정보 목록, 선택된 기기의 번호, 선택된 기기 번호 변경 함수로 머신 카드를 그립니다 */}
      <MachineCard options={options} machine={machine} selected={selected} setSelected={setSelected} />
    </div>
  );
};

export default MainPage;
