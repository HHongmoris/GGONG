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
    useApi('/stat/today', 'GET')
      .then(res => {
        // 요청이 성공적으로 처리된 경우, response.data를 사용하여 데이터를 업데이트합니다.
        setToday(res.data.todayUser);
      })
      .catch(e => {
        console.error(e.message);
      });

    useApi('/stat/user', 'GET')
      .then(res => {
        setUsers(res.data);
      })
      .catch(e => {
        console.error(e.message);
      });

    useApi('/stat/age', 'GET')
      .then(res => {
        setAges(res.data);
      })
      .catch(e => {
        console.error(e.message);
      });

    useApi('/stat/gender', 'GET')
      .then(res => {
        setGender(res.data);
      })
      .catch(e => {
        console.error(e.message);
      });

    useApi('/stat/machine', 'GET')
      .then(res => {
        setMachine(res.data);
      })
      .catch(e => {
        console.error(e.message);
      });
  }, []);

  return (
    <div>
      <StatPage today={today} users={users} pieAges={ages} pieGender={gender} machineUsers={machine} />
    </div>
  );
};

export default StatContainer;
