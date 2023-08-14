import React, { useState } from 'react';

import icons from '../../global/icons';

import Select from '../Select/Select';
import IconButton from '../Button/IconButton';
import LineChart from '../Chart/LineChart';
import BarChart from '../Chart/BarChart';
import useApi from '../../hooks/useApi';
import { Background, Border } from '../../global/colors';

const MachineCard = ({ selected, setSelected, machines = [], options = [] }) => {
  const [toggle, setToggle] = useState(true);

  // // onClick 이벤트 함수 만들어서 넣어주면 될 듯!
  // // 등록 삭제 조건 걸어주기!
  // const likeFunction = toggle => {
  //   if (toggle) {
  //     // console.log(`1: ${toggle}`);
  //     useApi(`/machines/${machineNo}`, 'DELETE').then();
  //     console.log('DELETE 성공');
  //   } else {
  //     // console.log(`2: ${toggle}`);
  //     useApi(`/machines/${machineNo}`, 'POST').then();
  //     console.log('POST 성공');
  //   }
  // };

  const borderColor = Border['MAIN'];
  const bgColor = Background['WHITE'];

  return (
    <div className={`card border ${bgColor} ${borderColor}`}>
      <div className="card-body">
        {/* <div className="flex justify-start">
          <Select options={options} selected={selected} setSelected={setSelected} />
        </div> */}
        {/* 기기의 정보를 통해 차트를 그립니다. 선택된 기기번호와 같은 기기의 카드만 화면에 보입니다. */}
        {machines.map((machine, idx) => {
          const { machineName, machineNo, userCount, optionA, answerA, optionB, answerB, rateA, rateB } = machine;
          const votes = [
            { label: optionA, value: answerA, ratio: rateA },
            { label: optionB, value: answerB, ratio: rateB },
          ];
          // onClick 이벤트 함수 만들어서 넣어주면 될 듯!
          // 등록 삭제 조건 걸어주기!
          const likeFunction = toggle => {
            if (toggle) {
              // console.log(`1: ${toggle}`);
              useApi(`/machines/${machineNo}`, 'POST').then();
              console.log(machineNo, 'POST 성공');
            } else {
              // console.log(`2: ${toggle}`);
              useApi(`/machines/${machineNo}`, 'DELETE').then();
              console.log(machineNo, 'DELETE 성공');
            }
          };
          // console.log(machine, votes);
          return (
            machineNo == selected && (
              <div key={idx}>
                <div className="flex justify-between">
                  <Select options={options} selected={selected} setSelected={setSelected} />
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
                <LineChart machineName={machineName} userCount={userCount} />
                <div className="divider"></div>
                <BarChart title="현재 진행중인 투표" data={votes} />
              </div>
            )
          );
        })}
      </div>
    </div>
  );
};

export default MachineCard;
