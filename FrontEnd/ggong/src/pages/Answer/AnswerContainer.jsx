import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import AnswerPage from './AnswerPage';
import useApi from '../../hooks/useApi';

// 질문 응답 페이지에서 사용할 데이터를 관리하는 컨테이너
const AnswerContainer = () => {
  const [titleContent, setTitleContent] = useState('');
  const [voteData, setVoteData] = useState([[]]);

  const location = useLocation();

  // console.log(titleContent);
  // console.log(location.pathname);
  // console.log(voteData);

  // path에 따라 다른 데이터 받기, 타이틀 정보도 다르게 받음.
  useEffect(() => {
    if (location.pathname === '/answers/present') {
      useApi('/answers/present', 'GET').then(res => {
        setVoteData(res.data);
        setTitleContent('진행 중인 투표');
      });
    } else if (location.pathname === '/answers') {
      useApi('/answers', 'GET').then(res => {
        setVoteData(res.data);
        setTitleContent('지난 투표');
      });
    }
  }, [location]);

  return (
    <div>
      <AnswerPage titleContent={titleContent} voteData={voteData} />
    </div>
  );
};

export default AnswerContainer;
