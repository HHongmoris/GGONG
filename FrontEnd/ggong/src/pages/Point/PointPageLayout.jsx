import React from 'react';
import ProfileCard from '../../components/Card/ProfileCard';
import { Outlet } from 'react-router-dom';

import { useSelector } from 'react-redux';

// 포인트 관련 페이지에 공통으로 적용되는 레이아웃
const PointPageLayout = () => {
  const { userRating, nickname, points } = useSelector(state => state.user);

  return (
    <div>
      <ProfileCard grade={userRating} nickname={nickname} point={points} />
      <Outlet />
    </div>
  );
};

export default PointPageLayout;
