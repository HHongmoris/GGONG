import React from 'react';
import { Border } from '../../global/colors';

// TableItem 컴포넌트 -> 테이블 한 행을 구성, 기기 번호와 기기이름 사용자 수를 파라미터로 받고,
// 스타일을 꾸며줬습니다.
const TableItem = ({ machineName = '기기 이름', userCnt = '몇 명' }) => {
  const borderColor = Border['MAIN'];

  return (
    <tr className={`border-2 border-solid ${borderColor}`}>
      <td className={`border-2 border-solid ${borderColor} text-center`}>{machineName}</td>
      <td className={`border-2 border-solid ${borderColor} text-right`}>{userCnt} 명</td>
    </tr>
  );
};

export default TableItem;
