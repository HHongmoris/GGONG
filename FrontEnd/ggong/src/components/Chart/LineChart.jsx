import React, { useEffect, useState } from 'react';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';
import { Line } from 'react-chartjs-2';
import { Subtitle } from '../Heading';

//  chart.js에서 필요한 스케일(scale)과 요소(element)들을 등록
ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);

const LineChart = ({ userCount = [] }) => {
  const now = new Date();
  const sHour = ('0' + (now.getHours() - 2)).slice(-2);
  const sMin = ('0' + now.getMinutes()).slice(-2);
  const start = `${sHour}:${sMin}`;

  const eHour = ('0' + (now.getHours() + 2)).slice(-2);
  const eMin = ('0' + now.getMinutes()).slice(-2);
  const end = `${eHour}:${eMin}`;

  const cnts = [];
  const stamp = [];

  userCount.forEach((count, idx) => {
    const hour = ('0' + Math.floor((idx * 15) / 60)).slice(-2);
    const minute = ('0' + ((idx * 15) % 60)).slice(-2);

    const time = `${hour}:${minute}`;

    if (start < time && time < end) {
      cnts.push(count);
      stamp.push(`${hour}:${minute}`);
    }
  });

  const options = {
    responsive: true,
    plugins: {
      legend: false,
    },
    scales: {
      y: {
        min: 0, // 최소값을 0으로 설정
      },
    },
  };
  // x축에 표시될 라벨들을 지정
  const labels = stamp; //x축 기준

  const data = {
    labels,
    datasets: [
      {
        data: cnts, //실제 그려지는 데이터(Y축 숫자)
        borderColor: '#60a5fa', // 그래프 선 색
        backgroundColor: '#fb7185', // 포인트 색
      },
    ],
  };

  return (
    <div className="contentWrap">
      <Subtitle content="예상 혼잡도" spacing={true} />
      <div className="contentInner">
        <Line options={options} data={data} />
      </div>
    </div>
  );
};

export default LineChart;
