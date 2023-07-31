import { PieChart as Pie } from 'react-minimal-pie-chart';

const data = [
  {
    title: '남성',
    value: 10,
    color: 'blue',
  },
  {
    title: '여성',
    value: 3,
    color: 'red',
  },
];

const PieChart = () => {
  return (
    <div>
      <Pie
        data={data}
        style={{ width: '300px', height: '300px' }}
        label={({ x, y, dx, dy, dataEntry }) => (
          <text
            x={x}
            y={y}
            dx={dx}
            dy={dy}
            dominant-baseline="central"
            text-anchor="middle"
            style={{
              fill: '#fff',
              pointerEvents: 'none',
              fontSize: '0.5rem',
            }}
          >
            <tspan x={x} y={y - 10} dx={dx} dy={dy}>
              {dataEntry.title}
            </tspan>
            <tspan x={x} y={y} dx={dx} dy={dy}>{`${Math.round(dataEntry.value)}%`}</tspan>
          </text>
        )}
        startAngle={-90}
      />
    </div>
  );
};

export default PieChart;
