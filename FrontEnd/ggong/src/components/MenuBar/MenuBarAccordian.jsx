import React from 'react';
import MenuBarItem from './MenuBarItem';

// 아코디언이 들어간 메뉴아이템들을 만드는 컴포넌트
// 메뉴 이름과 하위 메뉴이름, 하위메뉴에 연결된 링크, 아코디언을 열고 닫는 기능을 관리할 변수들
const MenuBarAccordian = ({
  title = 'menuName',
  accordianMenu1 = '하위 1번메뉴',
  link1 = 'link1',
  accordianMenu2 = '하위 2번메뉴',
  link2 = 'link2',
  accordianMenu3,
  link3,
  isOpen,
  toggleAccordian,
}) => {
  // 아코디언의 열림/닫힘 상태를 관리하기 위한 state를 정의합니다.
  // 초기엔 닫혀있다.

  return (
    // MenuBarItem 컴포넌트를 이용해서 하위 메뉴들 구현해줌. 토글에는 열고 닫는 것을 관리할 값들 할당
    <div className="collapse collapse-arrow bg-base-200">
      <input type="radio" name="my-accordion-2" checked={isOpen} onChange={toggleAccordian} />
      <div className="collapse-title text-xl font-medium" onClick={toggleAccordian}>
        {title}
      </div>
      <div className="collapse-content">
        <MenuBarItem menuName={`${accordianMenu1}`} link={link1} />
        <MenuBarItem menuName={`${accordianMenu2}`} link={link2} />
        {accordianMenu3 && <MenuBarItem menuName={`${accordianMenu3}`} link={link3} />}
      </div>
    </div>
  );
};

export default MenuBarAccordian;
