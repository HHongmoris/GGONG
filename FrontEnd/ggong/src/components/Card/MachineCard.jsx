import React from 'react';

import icons from '../../global/icons';

import Select from '../Select/Select';
import IconButton from '../Button/IconButton';
import LineChart from '../Chart/LineChart';
import BarChart from '../Chart/BarChart';

const MachineCard = ({ machine = {} }) => {
  return (
    <div className="card border border-black bg-white">
      <div className="card-body">
        <div className="flex justify-between">
          <Select />
          <IconButton icon={icons.HEART} toggle={true} toggleIcon={icons.HEART_FILL} />
        </div>
        <div>
          <LineChart />
          <div className="divider"></div>
          <BarChart title="현재 진행중인 투표" />
        </div>
      </div>
    </div>
  );
};

export default MachineCard;
