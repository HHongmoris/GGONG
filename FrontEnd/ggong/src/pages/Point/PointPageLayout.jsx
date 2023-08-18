import React, { useEffect } from 'react';
import ProfileCard from '../../components/Card/ProfileCard';
import { Outlet } from 'react-router-dom';
import useApi from '../../hooks/useApi';
import { useSelector, useDispatch } from 'react-redux';
import { changePoint } from '../../global/store'; // import 로그인 액션

// 포인트 관련 페이지에 공통으로 적용되는 레이아웃
const PointPageLayout = () => {
  const { userRating, nickname, points } = useSelector(state => state.user);
  const dispatch = useDispatch(); // 디스패치 함수 가져오기

  const user = useSelector(state => state.user);

  // points 값이 변경될 때마다 실행되는 useEffect
  useEffect(() => {
    const newPoints = user.points;
    // Redux 스토어의 points 값을 업데이트, 나머지는 그대로 적용
    dispatch(changePoint({ points: newPoints }));
  }, [dispatch]); // dispatch 함수가 변경될 때마다 실행

  return (
    <div className="mx-5 pb-5">
      <div className="mb-8">
        <ProfileCard grade={userRating} nickname={nickname} point={points} />
      </div>
      <Outlet />
    </div>
  );
};

export default PointPageLayout;
