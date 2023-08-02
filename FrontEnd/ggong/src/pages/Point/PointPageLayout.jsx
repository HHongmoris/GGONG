import React from 'react';
import ProfileCard from '../../components/Card/ProfileCard';
import { Outlet } from 'react-router-dom';

// 포인트 관련 페이지에 공통으로 적용되는 레이아웃
const PointPageLayout = () => {
  return (
    <div>
      <ProfileCard />
      <Outlet />
    </div>
  );
};

export default PointPageLayout;
