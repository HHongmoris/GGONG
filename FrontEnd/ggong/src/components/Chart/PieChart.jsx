import { Border } from '../../global/colors';

import { PieChart as Pie } from 'react-minimal-pie-chart';

import { Subtitle } from '../Heading';

/**
 * 입력받은 데이터로 파이차트를 그려주는 컴포넌트 <br>
 *
 * Data: {
 *
 *  title: 데이터 라벨,
 *
 *  value: 데이터 값
 *
 * }
 * @param {string} title 차트의 제목
 * @param {Data[]} data 파이 차트로 나타낼 데이터 배열
 * @param {boolean} showNarrowLabel 적은 데이터 영역의 라벨을 표시할지 나타내는 변수
 * @returns 입력받은 데이터로 나타낸 파이 차트
 */
const PieChart = ({ title, data = [], showNarrowLabel = false }) => {
  // 받아온 데이터에 순서대로 정해진 색깔을 할당
  // 파랑, 빨강, 회색, 노랑, 보라
  const colors = ['#60a5fa', '#f87171', '#94a3b8', '#facc15', '#a78bfa'];
  data.forEach((datum, idx) => (datum.color = colors[idx]));

  // 파이 차트
  return (
    <div className="w-2/5 text-center">
      {/* 차트 제목 */}
      <Subtitle content={title} />
      {/* <div className={`border border-2 ${Border.MAIN} flex justify-center items-center`}> */}
      <div className={'flex justify-center items-center'}>
        {/* 파이 차트 */}
        <Pie
          data={data}
          // 데이터 영역 사이마다 여백
          paddingAngle={0.5}
          animate
          // 차트의 크기
          viewBoxSize={[100, 100]}
          // 데이터 라벨
          label={({ x, y, dx, dy, dataEntry }) =>
            (showNarrowLabel || dataEntry.percentage >= 5) && (
              <text
                x={x}
                y={y}
                dx={dx}
                dy={dy}
                // 라벨을 영역 중앙에 표시해 줌
                textAnchor="middle"
                style={{
                  fill: '#fff',
                  pointerEvents: 'none',
                  fontSize: title === '연령별' ? '0.45rem' : '0.55rem',
                }}
              >
                {/* 데이터 라벨 제목, 차지하는 비율을 표시 */}
                <tspan x={x} y={y - 5} dx={dx} dy={dy}>
                  {dataEntry.label}
                </tspan>
                <tspan x={x} y={y + 5} dx={dx} dy={dy}>{`${Math.round(dataEntry.percentage)} %`}</tspan>
              </text>
            )
          }
          labelPosition={title === '연령별' ? 65 : 55}
          startAngle={-90}
          className="m-1"
        />
      </div>
    </div>
  );
};

export default PieChart;
