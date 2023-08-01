import React from 'react';
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

//  chart.js에서 필요한 스케일(scale)과 요소(element)들을 등록
ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);
const options = {
  responsive: true,
  plugins: {
    legend: false,
    title: {
      display: true,
      text: '예상 혼잡도',
    },
  },
};

// x축에 표시될 라벨들을 지정
const labels = ['09:00', '09:15', '09:30', '09:45', '10:00', '10:15', '10:30', '10:45', '11:00']; //x축 기준

const data = {
  labels,
  datasets: [
    {
      data: [3, 7, 10, 4, 6, 5, 25, 17, 8], //실제 그려지는 데이터(Y축 숫자)
      borderColor: '#60a5fa', // 그래프 선 색
      backgroundColor: '#fb7185', // 포인트 색
    },
  ],
};

const LineChart = () => {
  return (
    <div className="contentWrap">
      <div className="contentInner">
        <Line options={options} data={data} />
      </div>
    </div>
  );
};

export default LineChart;
