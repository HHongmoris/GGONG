import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { Drawer } from 'react-daisyui';
import NewMenuBar from './MenuBar';
import IconButton from '../Button/IconButton';
import icons from '../../global/icons';
import Logo from '../../assets/logo.png';
import { Background } from '../../global/colors';

// 상단 네비게이션바 컴포넌트
const NavBar = () => {
  const [visible, setVisible] = useState(false);

  const toggleVisible = () => {
    setVisible(!visible);
  };

  const bgColor = Background['MAIN'];

  return (
    <Drawer
      className="z-40"
      open={visible}
      onClickOverlay={toggleVisible}
      end={true}
      // 햄버거 버튼을 누르면 나타나는 드로워 영역
      side={<NewMenuBar handleClick={toggleVisible} />}
    >
      {/* 메뉴 버튼을 누르지 않았을 때 상단에 표시되는 좌측의 홈 버튼, 우측의 햄버거 버튼 영역 */}
      <div className={`w-full ${bgColor} mb-8 flex justify-between items-center`}>
        <Link className="mx-4 w-1/12" to="/">
          <img src={Logo} />
        </Link>
        <IconButton icon={icons.MENU} onClick={toggleVisible} />
      </div>
    </Drawer>
  );
};

export default NavBar;
