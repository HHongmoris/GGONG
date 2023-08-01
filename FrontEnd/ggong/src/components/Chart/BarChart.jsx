import React from 'react';

import { Subtitle } from '../Heading';

const BarChart = ({ title, data }) => {
  const [A, B] = data;

  return (
    <div>
      <Subtitle content={title} spacing={true} />
      <div className="flex text-white">
        <div style={{ width: A.ratio + '%' }} className={'flex justify-between items-center bg-blue-400 h-10 px-2'}>
          <div>{A.label}</div>
          <div>{A.value}</div>
        </div>
        {/* <div className={'bg-blue-400 w-[4%]'}>{A.label}</div> */}
        <div className="flex items-center bg-gradient-to-r from-blue-400 to-red-400 h-10 text-xl">VS</div>

        <div style={{ width: B.ratio + '%' }} className={'flex justify-between items-center bg-red-400 h-10 px-2'}>
          <div>{B.value}</div>
          <div>{B.label}</div>
        </div>
      </div>
    </div>
  );
};

export default BarChart;
