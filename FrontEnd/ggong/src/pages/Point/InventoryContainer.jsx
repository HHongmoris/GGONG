import React, { useEffect, useState } from 'react';

import InventoryPage from './InventoryPage';
import useApi from '../../hooks/useApi';

// 보관함 페이지에 필요한 데이터를 관리하는 컨테이너
const InventoryContainer = () => {
  // 회원이 구매한 상품들의 목록
  const [bought, setBougth] = useState([]);

  // 컨테이너가 렌더링되면 api를 호출해 구매내역을 불러옵니다.
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
