import React from 'react';
import { Border } from '../global/colors';

// 임시로 넣어둔 테이블 데이터... 나중에 데이터 받아오면 없애면 됩니다.
const TableData = [
  {
    machineNum: 1,
    machineName: '역삼 멀티캠퍼스',
    userNum: 358 + '명',
  },
  {
    machineNum: 2,
    machineName: '역삼 멀티캠퍼스',
    userNum: 400 + '명',
  },
  {
    machineNum: 3,
    machineName: '역삼 멀티캠퍼스',
    userNum: 500 + '명',
  },
  {
    machineNum: 4,
    machineName: '역삼 멀티캠퍼스',
    userNum: 250 + '명',
  },
  {
    machineNum: 5,
    machineName: '역삼 멀티캠퍼스',
    userNum: 210 + '명',
  },
  {
    machineNum: 6,
    machineName: '역삼 멀티캠퍼스',
    userNum: 173 + '명',
  },
  {
    machineNum: 7,
    machineName: '역삼 멀티캠퍼스',
    userNum: 623 + '명',
  },
  {
    machineNum: 8,
    machineName: '역삼 멀티캠퍼스',
    userNum: 352 + '명',
  },
  {
    machineNum: 9,
    machineName: '역삼 멀티캠퍼스',
    userNum: 342 + '명',
  },
  {
    machineNum: 10,
    machineName: '역삼 멀티캠퍼스',
    userNum: 772 + '명',
  },
];

// TableItem 컴포넌트 -> 테이블 한 행을 구성, 기기 번호와 기기이름 사용자 수를 파라미터로 받고,
// 스타일을 꾸며줬습니다.
const TableItem = ({ machineNum = '기기 번호', machineName = '기기 이름', userNum = '몇 명' }) => {
  return (
    <tr className={`border-2 border-solid ${Border.MAIN}`}>
      <th className={`border-2 border-solid ${Border.MAIN}`}>{machineNum}</th>
      <td className={`border-2 border-solid ${Border.MAIN}`}>{machineName}</td>
      <td className={`border-2 border-solid ${Border.MAIN}`}>{userNum}</td>
    </tr>
  );
};

export { TableData, TableItem };
