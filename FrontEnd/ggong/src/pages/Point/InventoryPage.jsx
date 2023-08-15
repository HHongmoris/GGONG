import React from 'react';
import InventoryList from '../../components/List/InventoryList';

// 상품보관함 페이지
const InventoryPage = ({ products = [] }) => {
  return (
    <div>
      <InventoryList products={products} />
    </div>
  );
};

export default InventoryPage;
