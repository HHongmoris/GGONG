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
    if (location.pathname === '/vote/current') {
      setTitleContent('현재 진행중인 투표');

      const eventSource = new EventSource('http://i9a304.p.ssafy.io:8080/api/answers/present');

      eventSource.onopen = () => {
        console.log('연결');
      };

      eventSource.addEventListener('allAnswers', res => {
        const answers = JSON.parse(res.data);
        setVoteData(answers);
      });

      return () => {
        eventSource.close();
        console.log('종료');
      };
    } else if (location.pathname === '/vote/past') {
      useApi('/answers', 'GET').then(res => {
        setTitleContent('지난 투표');
        setVoteData(res.data);
      });
    }
  }, [location]);

  return (
    <div>
      <AnswerPage className="mx-5 pb-5" titleContent={titleContent} voteData={voteData} />
    </div>
  );
};

export default AnswerContainer;
