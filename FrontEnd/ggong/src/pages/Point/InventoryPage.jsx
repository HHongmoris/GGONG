import React from 'react';
import InventoryList from '../../components/List/InventoryList';

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

// 보관함 페이지
const InventoryPage = () => {
  return (
    <div className="mt-12">
      <InventoryList products={inventoryData} />
    </div>
  );
};

export default InventoryPage;
