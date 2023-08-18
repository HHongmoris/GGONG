import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { Title } from '../../components/Heading';
import { Border } from '../../global/colors';
import { Tab, category } from '../../components/Tab';
import BarChart from '../../components/Chart/BarChart';

/**
 * 투표 페이지 컴포넌트
 *
 * @param {number} num 카테고리 정보
 * @param {string} titleContent 페이지 제목 (현재 or 지난 투표)
 * @param {Data[[]]} voteData 바 차트에 들어갈 데이터 (탭에 따라 구분)
 * @returns 통계 페이지
 */

// 페이지 num => 어떤 카테고리를 쓸지 0이면 ['공통', '대학', '기업'], 2면 ['지역별', '연령별', '기업별']
const AnswerPage = ({ num = 0, titleContent, voteData }) => {
  // 탭 활성화 관리 (처음 상태 활성화 탭은 첫번째 탭)
  const [activeTab, setActiveTab] = useState(0);

  // console.log(voteData);

  // 클릭했을 때 tabIndex에 해당하는 탭이 활성화
  const handleTabClick = tabIndex => {
    setActiveTab(tabIndex);
  };

  return (
    <div className="mx-5 pb-5">
      <Title content={titleContent} spacing={true} />
      {/* category, activeTab, onClick 이벤트 */}
      <Tab category={category[num]} activeTab={activeTab} onClick={handleTabClick} />
      {/* activeTab으로 voteData를 선정해서 그 안에 있는 데이터들을 map으로 탐색해서 띄워주기 */}
      {voteData[activeTab].map((data, idx) => {
        // data들을 다 쪼개서 받기
        const { optionA, optionB, rateA, rateB, answerA, answerB, content, questionID } = data;
        const votes = [
          // A, B로 구조분해 할당
          { label: optionA, value: answerA, ratio: rateA },
          { label: optionB, value: answerB, ratio: rateB },
        ];

        const isPresent = location.pathname === '/vote/current';

        return (
          <div key={idx}>
            <Link to={`/vote/detail${isPresent ? '/present' : ''}?id=${questionID}&content=${content}`}>
              <div className={`card border ${Border.MAIN} mt-4`}>
                <div className="p-4">
                  <BarChart title={content} data={votes} />
                </div>
              </div>
            </Link>
          </div>
        );
      })}
    </div>
  );
};

export default AnswerPage;
