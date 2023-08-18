import React from 'react';

import DataCard from '../../components/Card/DataCard';
import { Title } from '../../components/Heading';
import PieChart from '../../components/Chart/PieChart';
import Table from '../../components/Table/Table';

/**
 * 통계 페이지 컴포넌트
 *
 * @param {number} today 금일 이용자 수
 * @param {number[]} users 그 외 회원수 데이터 카드에 들어갈 데이터
 * @param {Data[]} pieUsers 연령별, 성별 이용자 수 파이 차트에 들어갈 데이터
 * @param {Data[]} machineUsers 기기별 사용자 수 테이블에 들어갈 데이터
 * @returns 통계 페이지
 */
const StatPage = ({ today, users = [], pieAges, pieGender, machineUsers = [] }) => {
  // 배열로 전달받은 users와 pieUsers props를 구조분해할당 합니다.
  const { totalUser, lastMonthUser } = users;

  return (
    <div className="mx-5 pb-5">
      {/* 데이터 카드 영역 */}
      <div className="flex justify-between mb-8">
        <DataCard title="금일 이용자" data={today} />
        <DataCard title="전체 회원 수" data={totalUser} />
      </div>
      <div className="flex justify-between mb-8">
        <DataCard title="지난달 총 이용자" data={lastMonthUser} smallText={true} />
        <DataCard title="지난달 평균 이용자" data={Math.round(lastMonthUser / 30)} smallText={true} />
      </div>
      {/* 파이 차트 영역 */}
      <div className="mb-8">
        <Title content="사용자 수" spacing={true} />
        <div className="flex justify-between">
          <PieChart title="연령별" data={pieAges} />
          <PieChart title="성별" data={pieGender} />
        </div>
      </div>
      {/* 테이블 영역 */}
      <div>
        <Title content="기기별 사용자 수" spacing={true} />
        <Table data={machineUsers} />
      </div>
    </div>
  );
};

export default StatPage;
