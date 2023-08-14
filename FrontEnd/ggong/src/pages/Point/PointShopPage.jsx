import React from 'react';
import ProductList from '../../components/List/ProductList';

// // 상품 종류
// const products = [
//   {
//     price: 5000,
//   },
//   {
//     price: 10000,
//   },
//   {
//     price: 50000,
//   },
// ];

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
