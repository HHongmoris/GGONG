import React from 'react';

import { Text } from '../../global/colors';
import { Title } from '../Heading';
import ProductListItem from './ProductListItem';

/**
 *
 * @param {Array} data 포인트 내역의 배열
 * @returns data를 토대로 포인트 내역 리스트 컴포넌트를 만들어서 반환
 */
const ProductList = ({ products = [] }) => {
  return (
    <div>
      <Title content="포인트샵" />
      <div className="divider"></div>
      {/* 각 내역을 PointListItem 형식으로 변환해서 표시 */}
      {products.map((product, idx) => {
        const { price } = product;
        return <ProductListItem key={idx} price={price} />;
      })}
    </div>
  );
};

export default ProductList;
