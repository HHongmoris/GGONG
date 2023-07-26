import React from 'react';

/**
 * 값들을 전달받아 버튼 컴포넌트를 반환하는 함수
 *
 * @param {string} value 버튼에 표시될 라벨
 * @param {string} icon 버튼에 표시될 아이콘
 * @param {function} handleClick 버튼을 클릭했을 때 실행할 함수
 * @param {string} 아이콘의 위치를 정하는 변수, ['left-icon', 'right-icon']
 * @returns 버튼 컴포넌트
 */
const Button = ({
  value = '버튼',
  icon,
  handleClick = () => {
    console.log('버튼 클릭함');
  },
  type = 'right-icon',
}) => {
  return (
    <button className="btn bg-yellow-400 text-zinc-600" onClick={handleClick}>
      {/* icon이 있으면 type에 따라 icon을 표시한다 */}
      {icon && type === 'left-icon' && <span>{icon}</span>}
      <span>{value}</span>
      {icon && type === 'right-icon' && <span>{icon}</span>}
    </button>
  );
};

export default Button;
