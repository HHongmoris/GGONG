import React from 'react';

import ProfileCard from '../../components/Card/ProfileCard';
import DataCard from '../../components/Card/DataCard';
import MachineCard from '../../components/Card/MachineCard';

// 메인 페이지
const MainPage = ({ user = {}, today = 0, yesterday = 0 }) => {
  const { grade = '', nickname = 'dsa', point = 0, qr = '' } = user;

  return (
    <div>
      <ProfileCard grade={grade} nickname={nickname} point={point} qr={qr} />
      <div className="flex justify-between my-20">
        {/* 오늘 담배 수 */}
        <DataCard title="오늘 투표수" data={today} />
        {/* 어제 담배 수 */}
        <DataCard title="어제 투표수" data={yesterday} />
      </div>
      <MachineCard />
    </div>
  );
};

export default MainPage;
