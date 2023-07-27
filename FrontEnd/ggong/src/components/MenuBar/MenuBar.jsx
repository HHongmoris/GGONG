import React, { useState } from 'react';
import MenuBarItem from './MenuBarItem';
import MenuBarAccordian from './MenuBarAccordian';
import { Subtitle } from '../Heading';

// 메뉴바를 구현한 컴포넌트 (닉네임과 이메일을 값으로 받아준다.)
const MenuBar = ({ nickname = '닉네임', email = '이메일' }) => {
  // 각각의 아코디언에 대한 상태와 상태 변경 함수를 useState를 통해 정의
  const [isAccordianOpen1, setIsAccordianOpen1] = useState(false);
  const [isAccordianOpen2, setIsAccordianOpen2] = useState(false);

  // 토글 상태 변경해줄 함수
  const toggleAccordian1 = () => {
    setIsAccordianOpen1(prevState => !prevState);
  };

  const toggleAccordian2 = () => {
    setIsAccordianOpen2(prevState => !prevState);
  };

  return (
    <div className="drawer drawer-end">
      <input id="my-drawer-4" type="checkbox" className="drawer-toggle" />
      <div className="drawer-content">
        <label htmlFor="my-drawer-4" className="drawer-button btn bg-yellow-400">
          {/* 햄버거 모양 아이콘 */}
          햄버거..
        </label>
      </div>
      <div className="drawer-side">
        <label htmlFor="my-drawer-4" className="drawer-overlay"></label>
        <ul className="menu p-4 w-80 h-full bg-base-200 text-base-content">
          {/* Sidebar content here */}
          <li className="bg-yellow-400">
            <Subtitle content={`${nickname}님, 환영합니다.`} />
            <Subtitle content={`${email}`} />
          </li>
          {/* 아이콘하고 링크만 바꿔서 사용하면 될 듯 합니다. */}
          <li>
            <MenuBarItem icon={<i className="icon-hamburger"></i>} menuName="마이페이지" link="https://www.naver.com" />
          </li>
          <li>
            <MenuBarItem icon={<i className="icon-hamburger"></i>} menuName="꽁꽁 지도" link="https://www.naver.com" />
          </li>
          <li>
            {/* 아이콘하고 링크만 바꿔서 사용하면 될 듯 합니다. */}
            <MenuBarAccordian
              title="포인트"
              accordianMenu1="포인트샵"
              link1="https://www.naver.com"
              accordianMenu2="상품 보관함"
              link2="https://www.naver.com"
              accordianMenu3={'포인트 내역'}
              link3={'https://www.naver.com'}
              isOpen={isAccordianOpen1}
              toggleAccordian={toggleAccordian1}
            />
          </li>
          <li>
            <MenuBarAccordian
              title="꽁꽁 투표"
              accordianMenu1="진행 중인 투표"
              link1="https://www.naver.com"
              accordianMenu2="지난 투표"
              link2="https://www.naver.com"
              isOpen={isAccordianOpen2}
              toggleAccordian={toggleAccordian2}
            />
          </li>
          <li>
            <MenuBarItem icon={<i className="icon-hamburger"></i>} menuName="꽁꽁 통계" link="https://www.naver.com" />
          </li>
        </ul>
      </div>
    </div>
  );
};

export default MenuBar;
