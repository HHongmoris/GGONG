import React from 'react';
import { Outlet } from 'react-router-dom';

// 질문 응답 페이지에서 사용할 데이터를 관리하는 컨테이너
const AnswerContainer = () => {
  return (
    <div>
      <Outlet />
    </div>
  );
};

export default AnswerContainer;
