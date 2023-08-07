import { Menu } from 'react-daisyui';
import { Link } from 'react-router-dom';
import { Subtitle } from '../Heading';
import { useSelector } from 'react-redux';
import icons from '../../global/icons';

// 햄버거 버튼을 누르면 표시되는 드로워 영역
const MenuBar = ({ handleClick }) => {
  const { nickname, email } = useSelector(state => state.user);

  return (
    <div className="w-80 h-full bg-base-200 text-base-content">
      {/* 상단 사용자 정보 영역 */}
      <div className="bg-yellow-400 p-5">
        <Subtitle content={`${nickname}님, 환영합니다.`} />
        {email}
      </div>
      {/* 선택한 항목들을 볼 수 있는 메뉴바, 링크를 클릭하면 드로워가 자동으로 닫힘 */}
      <Menu>
        <div className="p-4 text-lg">
          <Menu.Item onClick={handleClick}>
            <Link to="/mypage">{icons.USER} 마이페이지</Link>
          </Menu.Item>
          <Menu.Item onClick={handleClick}>
            <Link to="/map">{icons.MAP} 꽁꽁 지도</Link>
          </Menu.Item>
          <Menu.Item>
            <Menu.Details
              open={false}
              label={
                <div className="flex items-center">
                  <span className="mr-2">{icons.POINT}</span> 포인트
                </div>
              }
            >
              <Menu.Item onClick={handleClick}>
                <Link to="point/shop">포인트샵</Link>
              </Menu.Item>
              <Menu.Item onClick={handleClick}>
                <Link to="point/inventory">상품 보관함</Link>
              </Menu.Item>
              <Menu.Item onClick={handleClick}>
                <Link to="point/history">포인트 내역</Link>
              </Menu.Item>
            </Menu.Details>
          </Menu.Item>
          <Menu.Item>
            <Menu.Details
              open={false}
              label={
                <div className="flex items-center">
                  <span className="mr-2">{icons.VOTE}</span> 꽁꽁 투표
                </div>
              }
            >
              <Menu.Item onClick={handleClick}>
                <Link to="vote/current">진행 중인 투표</Link>
              </Menu.Item>
              <Menu.Item onClick={handleClick}>
                <Link to="vote/past">지난 투표</Link>
              </Menu.Item>
            </Menu.Details>
          </Menu.Item>
          <Menu.Item onClick={handleClick}>
            <Link to="/stat">{icons.STAT} 꽁꽁 통계</Link>
          </Menu.Item>
        </div>
      </Menu>
    </div>
  );
};

export default MenuBar;
