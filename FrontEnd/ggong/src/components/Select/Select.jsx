import React from 'react';
import SelectItem from './SelectItem';

// 전달받은 option 목록으로 select 컴포넌트를 만들어 반환합니다.
const Select = ({ selected, setSelected, options = [] }) => {
  // 처음에 default로 보이는 값은 canSelect를 false로 해서 disabled처리 하였고 나머지 option은 canSelect 값을 따로 주지 않아서
  // 기본인 true가 전달되도록 구현
  // map 함수를 이용해 option들 구현
  // 선호 담배 드롭다운으로 바꾸려면 관심기기를 선택해주세요 부분 태그를 날려버리면 됨.
  return (
    <div className="component-preview p-4 gap-2">
      <select value={selected} onChange={event => setSelected(event.target.value)}>
        <SelectItem sendValue={'default'} optionName="관심기기를 선택해주세요." canSelect={false} />
        {options.map((option, idx) => {
          const { sendValue, optionName } = option;
          return <SelectItem key={idx} sendValue={sendValue} optionName={optionName} />;
        })}
      </select>
    </div>
  );
};

export default Select;
