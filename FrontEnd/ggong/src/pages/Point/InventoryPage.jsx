import React from 'react';
import InventoryList from '../../components/List/InventoryList';

// 상품보관함 페이지
const InventoryPage = ({ products = [] }) => {
  return (
    <div className="mt-12">
      <InventoryList products={products} />
    </div>
  );
};

export default InventoryPage;
