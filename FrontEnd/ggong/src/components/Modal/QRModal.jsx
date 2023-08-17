import React, { useState } from 'react';

import icons from '../../global/icons';

import Button from '../Button/Button';
import QRModalContent from './QRModalContent';

const QRModal = ({ qr }) => {
  // 모달창이 보이는 상태를 저장하는 변수
  const [visible, setVisible] = useState(false);

  // visible의 상태를 토글하는 함수
  const toggleVisible = () => {
    setVisible(!visible);
  };

  return (
    <div>
      <Button value={icons.QR} handleClick={toggleVisible} square={true} />
      {/* 아래는 모달창 컴포넌트 */}
      <QRModalContent open={visible} toggleVisible={toggleVisible} qr={qr} />
    </div>
  );
};

export default QRModal;
