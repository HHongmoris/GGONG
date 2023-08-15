import React from 'react';
import { Link } from 'react-router-dom';

// 아이콘, 메뉴제목, 링크를 구현한 메뉴바아이템 컴포넌트
const MenuBarItem = ({ icon, menuName = 'menuName', link = 'link' }) => {
  return (
    <div>
      <span>{icon}</span>
      <Link to={link} className="text-xl">
        {menuName}
      </Link>
    </div>
  );
};

export default MenuBarItem;
