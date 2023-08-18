import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';

import MainPage from './MainPage';

import useApi from '../../hooks/useApi';
import { login } from '../../global/store';
import { useDispatch, useSelector } from 'react-redux';

// 메인 페이지에 필요한 데이터를 관리하는 컨테이너
const MainContainer = () => {
  // 오늘 투표수, 어제 투표수, 관심 기기, 선택 가능한 기기번호, 선택된 기기번호
  const [today, setToday] = useState(0);
  const [yesterday, setYesterday] = useState(0);
  const [machines, setMachines] = useState([]);
  const [options, setOptions] = useState([]);
  const [selected, setSelected] = useState(2);
  const [selectedMachine, setSelectedMachine] = useState({});
  // location 객체 생성 (현재 URL 정보 가져오기)
  const user = useSelector(state => state.user);
  const dispatch = useDispatch();
  const location = useLocation();

  // 컨테이너 렌더링 시 api 호출하여 데이터 불러오기
  // location 객체를 통해 URL을 불러올 때마다 재렌더링
  useEffect(() => {
    const { token } = user;

    useApi('/users', 'GET', token).then(res => {
      res.data.token = token;
      dispatch(login(res.data));
    });

    useApi('/users/smoke', 'GET', token).then(res => {
      setToday(res.data.currentCount);
      setYesterday(res.data.pastCount);
    });

    useApi('/machines', 'GET').then(res => {
      setMachines(res.data);
      const optionList = [];
      res.data.forEach(machine => optionList.push({ sendValue: machine.machineNo, optionName: machine.name }));
      setOptions(optionList);
    });
  }, [location.pathname]);

  useEffect(() => {
    const eventSource = new EventSource(`http://i9a304.p.ssafy.io:8080/api/machines/${selected}`);

    eventSource.addEventListener('detail', res => {
      setSelectedMachine(JSON.parse(res.data));
    });

    return () => {
      eventSource.close();
    };
  }, [selected]);

  return (
    <div>
      <MainPage
        user={user}
        today={today}
        yesterday={yesterday}
        machine={selectedMachine}
        options={options}
        selected={selected}
        setSelected={setSelected}
      />
    </div>
  );
};

export default MainContainer;
