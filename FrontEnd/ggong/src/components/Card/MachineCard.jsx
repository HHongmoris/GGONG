import React, { useState } from 'react';

import icons from '../../global/icons';

import Select from '../Select/Select';
import IconButton from '../Button/IconButton';
import LineChart from '../Chart/LineChart';
import BarChart from '../Chart/BarChart';
import useApi from '../../hooks/useApi';

const MachineCard = ({ machine = {} }) => {
  const [toggle, setToggle] = useState(true);

  // onClick 이벤트 함수 만들어서 넣어주면 될 듯!
  // 등록 삭제 조건 걸어주기!
  const likeFunction = toggle => {
    if (toggle) {
      // console.log(`1: ${toggle}`);
      useApi('/machines', 'DELETE').then();
      console.log('DELETE 성공');
    } else {
      // console.log(`2: ${toggle}`);
      useApi('/machines', 'POST').then();
      console.log('POST 성공');
    }
  };
  return (
    <div className="card border border-black bg-white">
      <div className="card-body">
        <div className="flex justify-between">
          <Select />
          <IconButton
            icon={icons.HEART}
            toggle={true}
            toggleIcon={icons.HEART_FILL}
            onClick={() => {
              setToggle(!toggle); // 클릭 상태를 반전시키고
              likeFunction(!toggle); // 반전된 상태를 likeFunction에 전달
            }}
          />
        </div>
        <div>
          {/* <LineChart /> */}
          <div className="divider"></div>
          {/* <BarChart title="현재 진행중인 투표" /> */}
        </div>
      </div>
    </div>
  );
};

export default MachineCard;
