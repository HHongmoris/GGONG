import React from 'react';

import Select from '../Select/Select';
import LineChart from '../Chart/LineChart';
import BarChart from '../Chart/BarChart';
import { Background, Border } from '../../global/colors';

const MachineCard = ({ selected, setSelected, options = [], machine = {} }) => {
  const { name, userCount, optionA, answerA, optionB, answerB, rateA, rateB, content } = machine;
  const votes = [
    { label: optionA, value: answerA, ratio: rateA },
    { label: optionB, value: answerB, ratio: rateB },
  ];

  const borderColor = Border['MAIN'];
  const bgColor = Background['WHITE'];

  return (
    <div className={`card border ${bgColor} ${borderColor}`}>
      <div className="card-body">
        {/* <div className="flex justify-start">
          <Select options={options} selected={selected} setSelected={setSelected} />
        </div> */}
        {/* 기기의 정보를 통해 차트를 그립니다. 선택된 기기번호와 같은 기기의 카드만 화면에 보입니다. */}

        <div className="flex justify-between">
          <Select options={options} selected={selected} setSelected={setSelected} />
        </div>
        <LineChart machineName={name} userCount={userCount} />
        <div className="divider"></div>
        <BarChart title={content} data={votes} />
      </div>
    </div>
  );
};

export default MachineCard;
