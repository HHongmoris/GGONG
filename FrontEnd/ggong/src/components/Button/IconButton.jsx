import React, { useState } from 'react';

/**
 * 값들을 전달받아 아이콘 버튼 컴포넌트를 반환하는 함수
 *
 * @param {string} icon 기본적으로 버튼에 표시할 아이콘
 * @param {function} onClick 버튼을 클릭했을 때 실행할 함수
 * @param {boolean} toggle 토글 버튼인지 여부
 * @param {string} toggleIcon 토글됐을 때 표시할 아이콘
 * @returns 아이콘 버튼 컴포넌트
 */
const IconButton = ({
  icon,
  onClick = () => {
    // onClick을 다르게 주면 되겠다!
    console.log('버튼 클릭함');
  },
  toggle = false,
  toggleIcon,
  size = false,
}) => {
  // 토글 여부를 저장하는 변수, 기본은 false
  const [clicked, setClicked] = useState(false);
  // 클릭할 때마다 상태가 토글됨
  const handleClick = () => {
    setClicked(!clicked);
    onClick();
  };

  return (
    // 토글 버튼이면서 클릭됐으면 toggleIcon을 출력, 그 외에는 icon을 출력
    <button className={`btn btn-circle ${size && 'btn-sm'} bg-yellow-400 text-zinc-600 `} onClick={handleClick}>
      {toggle && clicked ? toggleIcon : icon}
    </button>
  );
};

export default IconButton;
