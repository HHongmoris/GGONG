import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';

import MainPage from './MainPage';

import useApi from '../../hooks/useApi';
import { useSelector } from 'react-redux';

import axios from 'axios';

// 메인 페이지에 필요한 데이터를 관리하는 컨테이너
const MainContainer = () => {
  // 오늘 투표수, 어제 투표수, 관심 기기, 선택 가능한 기기번호, 선택된 기기번호
  const [today, setToday] = useState(0);
  const [yesterday, setYesterday] = useState(0);
  const [likes, setLikes] = useState([]);
  const [options, setOptions] = useState([]);
  const [selected, setSelected] = useState(0);

  // location 객체 생성 (현재 URL 정보 가져오기)
  const user = useSelector(state => state.user);
  const location = useLocation();

  // 컨테이너 렌더링 시 api 호출하여 데이터 불러오기
  // location 객체를 통해 URL을 불러올 때마다 재렌더링
  useEffect(() => {
    const { token } = user;
    console.log('메인페이지', user, token);

    token &&
      useApi('/users/smoke', 'GET', token).then(res => {
        console.log('smoke done');
        setToday(res.data.currentCount);
        setYesterday(res.data.pastCount);
      });

    useApi('/users/like', 'GET', token)
      .then(res => {
        setLikes(res.data);
      })
      .catch(err => console.log(err));
  }, [location]);

  //   axios({
  //     // API 서버 URL
  //     url: 'http://localhost:8000/users/like',
  //     // HTTP 메서드
  //     method: 'GET',
  //     // 요청 헤더 설정
  //     headers: {
  //       'Content-Type': 'application/json',
  //     },
  //   }).then(res => setLikes(res.data));
  // }, [location]);

  // 관심 기기목록에 변동이 생기면 Select 태그로 선택가능한 옵션의 목록과 선택 default 값을 반환합니다.
  useEffect(() => {
    const optionList = [];
    likes.forEach(machine => optionList.push({ sendValue: machine.machineNo, optionName: machine.machineName }));
    setOptions(optionList);
    setSelected(likes.length !== 0 ? likes[0]['machineNo'] : 0);
  }, [likes]);
  console.log(user);

  return (
    <div>
      <MainPage
        user={user}
        today={today}
        yesterday={yesterday}
        machines={likes}
        options={options}
        selected={selected}
        setSelected={setSelected}
      />
    </div>
  );
};

export default MainContainer;
