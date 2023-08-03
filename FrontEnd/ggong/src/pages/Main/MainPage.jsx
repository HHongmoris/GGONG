import React from 'react';

import ProfileCard from '../../components/Card/ProfileCard';
import DataCard from '../../components/Card/DataCard';
import MachineCard from '../../components/Card/MachineCard';

// 메인 페이지
const MainPage = ({ user = {}, today = 0, yesterday = 0, machines = [] }) => {
  const { userRating = '', nickname = '', points = 0, QR = '' } = user;

  return (
    <div>
      <ProfileCard grade={userRating} nickname={nickname} point={points} qr={QR} />
      <div className="flex justify-between my-20">
        {/* 오늘 담배 수 */}
        <DataCard title="오늘 투표수" data={today} />
        {/* 어제 담배 수 */}
        <DataCard title="어제 투표수" data={yesterday} />
      </div>
      {/* <MachineCard machines={machines} /> */}
    </div>
  );
};

export default MainPage;
