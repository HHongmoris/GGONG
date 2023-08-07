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

  // points 값이 변경될 때마다 실행되는 useEffect
  useEffect(() => {
    // API 호출하여 points 값을 업데이트
    useApi('/users/1', 'GET')
      .then(res => {
        // newPoints값 받아오기
        const newPoints = res.data[0].points;
        // nickname, userRating도 받아오기

        // Redux 스토어의 points 값을 업데이트, 나머지는 그대로 적용
        dispatch(changePoint({ points: newPoints }));
      })
      .catch(error => {
        console.error('API 호출 오류:', error);
      });
  }, [dispatch]); // dispatch 함수가 변경될 때마다 실행

  return (
    <div>
      <ProfileCard grade={userRating} nickname={nickname} point={points} />
      <Outlet />
    </div>
  );
};

export default PointPageLayout;
