import React, { useState } from 'react';

import icons from '../../global/icons';
/**
 * 값들을 전달받아 액션 버튼 컴포넌트를 반환하는 함수
 *
 * @param {string} icon 기본적으로 버튼에 표시할 아이콘
 * @param {string} description 액션 아이콘에 대한 설명
 * @param {function} onAction 버튼을 클릭했을 때 (action) 실행할 함수
 * @param {function} onCancel 버튼을 다시 클릭했을 (cancel) 때 실행할 함수
 * @returns 액션 버튼 컴포넌트
 */
const ActionButton = ({
  icon,
  description = '액션',
  onAction = () => {
    console.log('버튼 클릭함');
  },
  onCancel = () => {
    console.log('액션 취소');
  },
}) => {
  // 토글 여부를 저장하는 변수, 기본은 false
  const [clicked, setClicked] = useState(false);
  // 클릭하면 클릭 상태로 변경되고 action 이벤트 동작
  const handleAction = () => {
    setClicked(true);
    onAction();
  };
  // 클릭하면 미클릭 상태로 변경되고 cancel 이벤트 동작
  const handleCancel = () => {
    setClicked(false);
    onCancel();
  };

  return (
    // 토글 버튼이면서 클릭됐으면 toggleIcon을 출력, 그 외에는 icon을 출력
    <>
      {clicked ? (
        <button className="btn rounded-full shadow-lg shadow-zinc-300 bg-yellow-400 text-white" onClick={handleCancel}>
          <span>{description}</span>
          {icons.CANCEL}
        </button>
      ) : (
        <button className="btn btn-circle shadow-lg shadow-zinc-300 bg-yellow-400 text-white" onClick={handleAction}>
          {icon}
        </button>
      )}
    </>
  );
};

export default ActionButton;
