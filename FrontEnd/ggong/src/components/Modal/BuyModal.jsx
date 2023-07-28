import React, { useState } from 'react';

import Button from '../Button/Button';
import ModalContent from './BuyModalContent';

const BuyModal = () => {
  const [visible, setVisible] = useState(false);

  const toggleVisible = () => {
    setVisible(!visible);
  };

  return (
    <div>
      <Button value={'구매'} handleClick={toggleVisible} />
      <ModalContent open={visible} toggleVisible={toggleVisible} />
    </div>
  );
};

export default BuyModal;
