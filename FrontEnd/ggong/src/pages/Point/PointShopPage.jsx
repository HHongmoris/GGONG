import React from 'react';
import ProductList from '../../components/List/ProductList';

// 포인트샵 페이지
// 상품 리스트 불러오기
const PointShopPage = (products = []) => {
  return (
    <div>
      <ProductList products={products} />
    </div>
  );
};

export default PointShopPage;
