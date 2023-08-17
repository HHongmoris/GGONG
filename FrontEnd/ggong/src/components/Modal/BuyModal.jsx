import React, { useState } from 'react';

import Button from '../Button/Button';
import ModalContent from './BuyModalContent';

/**
 * 구매 모달 윈도우 컴포넌트를 반환하는 함수 <br>
 * 구매 버튼을 클릭하면 모달창이 열리고 확인, 취소 버튼을 선택할 수 있음 <br>
 *
 * @returns 구매 모달 컴포넌트
 */
const BuyModal = ({ price }) => {
  // 모달창이 보이는 상태를 저장하는 변수
  const [visible, setVisible] = useState(false);

  // visible의 상태를 토글하는 함수
  const toggleVisible = () => {
    setVisible(!visible);
  };

  return (
    <div>
      <Button value={'구매'} handleClick={toggleVisible} size="small" />
      {/* 아래는 모달창 컴포넌트 */}
      <ModalContent open={visible} toggleVisible={toggleVisible} price={price} />
    </div>
  );
};

export default BuyModal;
