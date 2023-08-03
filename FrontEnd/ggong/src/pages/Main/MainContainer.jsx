import React, { useEffect, useState } from 'react';

import MainPage from './MainPage';

import useApi from '../../hooks/useApi';
// 메인 페이지에 필요한 데이터를 관리하는 컨테이너
const MainContainer = () => {
  const [user, setUser] = useState({});
  const [today, setToday] = useState(0);
  const [yesterday, setYesterday] = useState(0);
  const [likes, setLikes] = useState([]);

  useEffect(() => {
    useApi('/users/1', 'GET').then(res => {
      setUser(res.data[0], console.log(user));
    });

    useApi('/users/smoke', 'GET').then(res => {
      setToday(res.data.currentCount);
      setYesterday(res.data.pastCount);
    });

    useApi('/users/like', 'GET').then(res => {
      setLikes(res.data);
    });
  }, []);

  return (
    <div>
      <MainPage user={user} today={today} yesterday={yesterday} machines={likes} />
    </div>
  );
};

export default MainContainer;
