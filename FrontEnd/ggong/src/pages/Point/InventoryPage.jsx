import React from 'react';
import InventoryList from '../../components/List/InventoryList';

// 구매한 상품 가상데이터
const inventoryData = [
  {
    price: 5000,
    PIN: 'ABCD-EFGH-ABCD-EFGH',
  },
  {
    price: 50000,
    PIN: '1234-5678-1234-5678',
  },
  {
    price: 10000,
    PIN: '1234-EFGH-1234-EFGH',
  },
  {
    price: 5000,
    PIN: 'ABCD-EFGH-ABCD-EFGH',
  },
  {
    price: 50000,
    PIN: 'ABCD-EFGH-ABCD-EFGH',
  },
  {
    price: 10000,
    PIN: 'ABCD-EFGH-ABCD-EFGH',
  },
];

// 상품보관함 페이지
const InventoryPage = ({ products = [] }) => {
  return (
    <div className="mt-12">
      <InventoryList products={products} />
    </div>
  );
};

export default InventoryPage;
