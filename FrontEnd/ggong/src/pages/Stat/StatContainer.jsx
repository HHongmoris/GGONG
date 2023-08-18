import React, { useState, useEffect } from 'react';
import useApi from '../../hooks/useApi';

import StatPage from './StatPage';

// 통계 페이지에 필요한 데이터를 관리하는 컨테이너
const StatContainer = () => {
  // useState로 상태 관리.. 객체 생성
  const [today, setToday] = useState();
  const [machine, setMachine] = useState();
  const [users, setUsers] = useState();
  const [ages, setAges] = useState();
  const [gender, setGender] = useState();

  // useEffect로 mount될 때 실행
  useEffect(() => {
    // useApi를 통해 해당 경로에 있는 데이터를 GET방식으로 받는다.
    // 'set변수'로 변수에 데이터 할당
    const eventSource = new EventSource('http://i9a304.p.ssafy.io:8080/api/stat/today');

    eventSource.addEventListener('todayUsers', res => {
      const userCount = JSON.parse(res.data)['todayUserCount'];
      setToday(userCount);
    });

    useApi('/stat/user', 'GET').then(res => {
      setUsers(res.data);
    });

    useApi('/stat/age', 'GET').then(res => {
      const ages = [];

      res.data.forEach(datum => {
        const ageData = {};

        const { ageRange, ageRangeCnt } = datum;

        ageData.label = ageRange;
        ageData.value = ageRangeCnt;

        ages.push(ageData);
      });

      setAges(ages);
    });

    useApi('/stat/gender', 'GET').then(res => {
      const genders = [];

      res.data.forEach(datum => {
        const genderData = {};

        const { gender, genderCnt } = datum;

        genderData.label = gender;
        genderData.value = genderCnt;

        genders.push(genderData);
      });

      setGender(genders);
    });

    useApi('/stat/machine', 'GET').then(res => {
      const machines = [];

      res.data.forEach(datum => {
        const machineCnt = {};

        const { machineName, userCount } = datum;

        machineCnt.machineName = machineName;
        machineCnt.userCnt = userCount;

        machines.push(machineCnt);
      });

      setMachine(machines);
    });

    return () => eventSource.close();
  }, []);

  return (
    <div>
      <StatPage today={today} users={users} pieAges={ages} pieGender={gender} machineUsers={machine} />
    </div>
  );
};

export default StatContainer;
