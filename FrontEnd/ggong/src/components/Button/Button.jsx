import React from 'react';

import { Background, Text } from '../../global/colors';

/**
 * 값들을 전달받아 버튼 컴포넌트를 반환하는 함수
 *
 * @param {string} value 버튼에 표시될 라벨
 * @param {string} icon 버튼에 표시될 아이콘
 * @param {function} handleClick 버튼을 클릭했을 때 실행할 함수
 * @param {false} 아이콘의 위치를 정하는 변수
 * @returns 버튼 컴포넌트
 */
const Button = ({
  value = '버튼',
  icon,
  handleClick = () => {},
  leftIcon = false,
  color = 'MAIN',
  size,
  square = false,
}) => {
  const bgColor = Background[color];
  const textColor = Text['MAIN'];

  return (
    <button
      className={`btn ${square ? 'btn-square' : ''} ${bgColor} ${textColor} ${
        size === 'small' && 'btn-sm'
      } border-none`}
      onClick={handleClick}
    >
      {/* icon이 있으면 type에 따라 icon을 표시한다 */}
      {icon && leftIcon && <span>{icon}</span>}
      <span>{value}</span>
      {icon && !leftIcon && <span>{icon}</span>}
    </button>
  );
};

export default Button;
