import React from 'react';

import DataCard from '../../components/Card/DataCard';
import { Title } from '../../components/Heading';
import PieChart from '../../components/Chart/PieChart';
import Table from '../../components/Table/Table';

// 통계 페이지
const StatPage = ({ today, users = [], pieUsers = [], machineUsers = [] }) => {
  const [userAll, userLastMonth] = users;
  const [userAge, userGender] = pieUsers;

  return (
    <div>
      <div className="flex justify-between mb-8">
        <DataCard title="금일 이용자" data={today} />
        <DataCard title="전체 회원 수" data={userAll} />
      </div>
      <div className="flex justify-between mb-8">
        <DataCard title="지난달 총 사용자" data={userLastMonth} />
        <DataCard title="지난달 평균 사용자" data={Math.round(userLastMonth / 30)} />
      </div>
      <div className="mb-8">
        <Title content="사용자 수" spacing={true} />
        <div className="flex justify-between">
          <PieChart title="연령별" data={userAge} />
          <PieChart title="성별" data={userGender} />
        </div>
      </div>
      <div>
        <Table data={machineUsers} />
      </div>
    </div>
  );
};

export default StatPage;
