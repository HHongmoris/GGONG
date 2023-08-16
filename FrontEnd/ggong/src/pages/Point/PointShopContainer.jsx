import React, { useState, useEffect } from 'react';

import { useLocation } from 'react-router-dom';
import { useSelector } from 'react-redux';
import PointShopPage from './PointShopPage';
import useApi from '../../hooks/useApi';

// 포인트샵 페이지에 필요한 데이터를 관리하는 컨테이너
const PointShopContainer = () => {
  // 상품 내역 데이터
  const [products, setProducts] = useState([]);
  const user = useSelector(state => state.user);

  // location 객체 생성 (현재 URL 정보 가져오기)
  const location = useLocation();

  // useEffect(() => {
  //   useApi('/items', 'GET', user.token).then(res => {
  //     setProducts(res.data);
  //   });
  // }, [location]);

  return (
    <div>
      <PointShopPage products={products} />
    </div>
  );
};

export default PointShopContainer;
