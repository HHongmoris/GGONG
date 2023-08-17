import React from 'react';

import { Title } from '../Heading';
import InventoryListItem from './InventoryListItem';

/**
 *
 * @param {Array} data 구매 내역의 배열
 * @returns data를 토대로 구매 내역 리스트 컴포넌트를 만들어서 반환
 */
const InventoryList = ({ products = [] }) => {
  return (
    <div>
      <Title content="구매내역" />
      <div className="divider"></div>
      {/* 각 내역을 InventoryListItem 형식으로 변환해서 표시 */}
      {products.length ? (
        products.map((product, idx) => {
          const { price, PIN } = product;
          return <InventoryListItem key={idx} price={price} PIN={PIN} />;
        })
      ) : (
        <div className="text-xl text-center">아직 구매한 상품이 없어요</div>
      )}
    </div>
  );
};

export default InventoryList;
