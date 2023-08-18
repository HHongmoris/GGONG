import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';

import { Border } from '../../global/colors';
import BarChart from '../../components/Chart/BarChart';
import { Tab, category } from '../../components/Tab';
import useApi from '../../hooks/useApi';
import { useLocation, useSearchParams } from 'react-router-dom';
import { Subtitle } from '../../components/Heading';

// 질문에 대한 응답 상세 페이지
const AnswerDetailPage = ({ num = 1 }) => {
  // 탭 활성화 관리 (처음 상태 활성화 탭은 첫번째 탭)
  const [activeTab, setActiveTab] = useState(0);
  const [detailData, setDetailData] = useState([[]]);
  const [search, setSearch] = useSearchParams();

  const location = useLocation();
  useEffect(() => {
    const questionId = search.get('id');

    if (location.pathname === '/vote/detail/present') {
      const eventSource = new EventSource(`http://i9a304.p.ssafy.io:8080/api/answers/present/${questionId}`);

      eventSource.addEventListener('detailAnswer', res => {
        const answers = JSON.parse(res.data);
        setDetailData(answers);
      });

      return () => {
        eventSource.close();
      };
    } else if (location.pathname === '/vote/detail') {
      useApi(`/answers/${questionId}`, 'GET').then(res => {
        setDetailData(res.data);
      });
    }
  }, [location.pathname]);

  // 클릭했을 때 tabIndex에 해당하는 탭이 활성화
  const handleTabClick = tabIndex => {
    setActiveTab(tabIndex);
  };

  return (
    <div className="mx-5 pb-5">
      <div className={`card border ${Border.MAIN} mb-4 p-4`}>
        {/* <Subtitle content="Q. 당신이 좋아하는 것은 홍성민이냐 갈비치킨이냐?" /> */}
        <Subtitle content={`Q. ${search.get('content')}`} />
      </div>
      <Tab category={category[num]} activeTab={activeTab} onClick={handleTabClick} />
      {detailData[activeTab].map((data, idx) => {
        // data들을 다 쪼개서 받기
        const { optionA, optionB, rateA, rateB, answerA, answerB, dataLabel } = data;
        const votes = [
          // A, B로 구조분해 할당
          { label: optionA, value: answerA, ratio: rateA },
          { label: optionB, value: answerB, ratio: rateB },
        ];

        return (
          <div key={idx}>
            <div className={`card border ${Border.MAIN} mt-4`}>
              <div className="p-4">
                <BarChart title={dataLabel} data={votes} />
              </div>
            </div>
          </div>
        );
      })}
    </div>
  );
};

export default AnswerDetailPage;
