import React from 'react';

// 아이콘, 메뉴제목, 링크를 구현한 메뉴바아이템 컴포넌트
const MenuBarItem = ({ icon, menuName = 'menuName', link = 'link' }) => {
  return (
    <div>
      <span>{icon}</span>
      <a href={link} className="text-xl">
        {menuName}
      </a>
    </div>
  );
};

export default MenuBarItem;
