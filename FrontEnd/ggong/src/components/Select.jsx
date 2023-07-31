import React, { useState } from 'react';
import { SelectItem } from './SelectItem';
import { SelectData } from './SelectItem';

// SelectData를 기본으로 받아준다.
const Select = ({ data = SelectData }) => {
  const [value, setValue] = useState('default');

  // 처음에 default로 보이는 값은 canSelect를 false로 해서 disabled처리 하였고 나머지 option은 canSelect 값을 따로 주지 않아서
  // 기본인 true가 전달되도록 구현
  // map 함수를 이용해 option들 구현
  // 선호 담배 드롭다운으로 바꾸려면 관심기기를 선택해주세요 부분 태그를 날려버리면 됨.
  return (
    <div className="flex w-full component-preview p-4 gap-2">
      <select value={value} onChange={event => setValue(event.target.value)}>
        <SelectItem sendValue={'default'} optionName="관심기기를 선택해주세요." canSelect={false} />
        {data.map(datum => {
          const { sendValue, optionName } = datum;
          return <SelectItem sendValue={sendValue} optionName={optionName} />;
        })}
      </select>
    </div>
  );
};

export default Select;
