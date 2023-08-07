import React from 'react';

// option 태그에서 서버에 전송될 값은 sendValue로 실제 option에 뜨는 이름은 optionName으로 구분
// canSelect : 이 옵션이 선택될 수 있는지에 대한 불리안
// 관심기기 선택 드롭다운의 경우와 선호 담배 선택에 대한 드롭다운의 경우에 기본으로 뜨는 옵션에 대한 disabled 설정을 바꿔줘야 함.
const SelectItem = ({ sendValue = '서버에 전송될 값', optionName = '옵션에 뜨는 이름', canSelect = true }) => {
  return (
    // value = 서버에 전송될 값, disabled는 !canSelect
    <option value={sendValue} disabled={!canSelect}>
      {optionName}
    </option>
  );
};

export default SelectItem;
