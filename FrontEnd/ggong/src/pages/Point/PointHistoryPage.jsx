import React from 'react';

import PointList from '../../components/List/PointList';

// datetime = 'yyyy-MM-dd(ddd) HH:MM'
// detail = '사용 내역'
// variant = 0, balance = 0

const pointdata = [
  {
    datetime: '2023-05-31(Wed) 17:30',
    detail: '역삼 멀티캠퍼스',
    variant: 50,
    balance: 50,
  },
  {
    datetime: '2023-06-07(Wed) 17:30',
    detail: '역삼 멀티캠퍼스',
    variant: 50,
    balance: 100,
  },
  {
    datetime: '2023-06-14(Wed) 17:30',
    detail: '역삼 멀티캠퍼스',
    variant: 150,
    balance: 250,
  },
  {
    datetime: '2023-06-21(Wed) 17:30',
    detail: '역삼 멀티캠퍼스',
    variant: 5000,
    balance: 5250,
  },
  {
    datetime: '2023-06-28(Wed) 17:30',
    detail: '역삼 멀티캠퍼스',
    variant: -5000,
    balance: 250,
  },
  {
    datetime: '2023-06-30(Fri) 17:30',
    detail: '역삼 멀티캠퍼스',
    variant: 50,
    balance: 300,
  },
  {
    datetime: '2023-07-07(Fri) 17:30',
    detail: '역삼 멀티캠퍼스',
    variant: 100,
    balance: 400,
  },
  {
    datetime: '2023-07-14(Fri) 17:30',
    detail: '역삼 멀티캠퍼스',
    variant: 200,
    balance: 600,
  },
  {
    datetime: '2023-07-14(Fri) 17:30',
    detail: '역삼 멀티캠퍼스',
    variant: 200,
    balance: 800,
  },
  {
    datetime: '2023-07-14(Fri) 17:30',
    detail: '역삼 멀티캠퍼스',
    variant: 200,
    balance: 1000,
  },
  {
    datetime: '2023-07-14(Fri) 17:30',
    detail: '역삼 멀티캠퍼스',
    variant: 200,
    balance: 1200,
  },
  {
    datetime: '2023-07-14(Fri) 17:30',
    detail: '역삼 멀티캠퍼스',
    variant: 200,
    balance: 1400,
  },
];

// 포인트 내역 페이지
const PointPage = () => {
  return (
    <div>
      <PointList data={pointdata} />
    </div>
  );
};

export default PointPage;
