import React, { useEffect, useState } from 'react';

import InventoryPage from './InventoryPage';
import useApi from '../../hooks/useApi';
// 보관함 페이지에 필요한 데이터를 관리하는 컨테이너
const InventoryContainer = () => {
  const [bought, setBougth] = useState([]);

  useEffect(() => {
    useApi('/items/buy', 'GET').then(res => {
      setBougth(res.data);
    });
  });

  return (
    <div>
      <InventoryPage products={bought} />
    </div>
  );
};

export default InventoryContainer;
