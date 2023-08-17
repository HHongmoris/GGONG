import React from 'react';
import TableItem from './TableItem';
import { Background, Border, Text } from '../../global/colors';

// 테이블 컴포넌트 -> 여기선 TableData를 기본으로 받음. 추후에 데이터 받아오면 바뀜
// 스크롤 구현을 위해 table-pin-rows를 적용, 테이블을 스타일에 맞게 꾸며줬음
// tbody는 받아온 data를 map함수를 이용해서 순회해서 각 datum에 있는 기기번호, 기기이름, 사용자 수를 받아서
// TableItem 컴포넌트에 넣어서 적용
const Table = ({ data = TableData }) => {
  const bgHeader = Background['MAIN'];

  const borderColor = Border['MAIN'];
  const textColor = Text['MAIN'];

  return (
    <div className="overflow-x-auto">
      <table className={`border-2 border-solid ${borderColor} table table-pin-rows `}>
        <thead>
          <tr>
            <th className={`border-2 border-solid ${borderColor} ${bgHeader} ${textColor} font-bold text-center w-3/4`}>
              기기명
            </th>
            <th className={`border-2 border-solid ${borderColor} ${bgHeader} ${textColor} font-bold text-center`}>
              사용자 수
            </th>
          </tr>
        </thead>
        <tbody className="bg-white">
          {data.map((datum, idx) => {
            const { machineNum, machineName, userCnt } = datum;
            return <TableItem key={idx} machineNum={machineNum} machineName={machineName} userCnt={userCnt} />;
          })}
        </tbody>
      </table>
    </div>
  );
};

export default Table;
