import './App.css';
import { Routes, Route, useNavigate } from 'react-router-dom';
import { Background } from './global/colors';

import React, { useEffect } from 'react';
import NavBar from './components/MenuBar/NavBar';

import LoginPage from './pages/Login/LoginPage';

import MainContainer from './pages/Main/MainContainer';
import MapContainer from './pages/Map/MapContainer';
import PointPageLayout from './pages/Point/PointPageLayout';
import AnswerContainer from './pages/Answer/AnswerContainer';
import StatContainer from './pages/Stat/StatContainer';

import PointShopContainer from './pages/Point/PointShopContainer';
import InventoryContainer from './pages/Point/InventoryContainer';
import PointHistoryContainer from './pages/Point/PointHistoryContainer';

import UserInfoPage from './pages/User/UserInfoPage';
import AnswerPage from './pages/Answer/AnswerPage';
import AnswerDetailPage from './pages/Answer/AnswerDetailPage';

import { useSelector } from 'react-redux';
import AuthProcess from './pages/Login/AuthProcess';

function App() {
  const user = useSelector(state => state.user);
  const bgColor = Background['GLOBAL'];
  const navigate = useNavigate();

  // 로그인 안돼있으면 로그인창으로 이동
  useEffect(() => {
    !user.token && navigate('/login');
  });

  return (
    <div className={`App min-h-screen ${bgColor}`}>
      <div>
        {user.token && <NavBar />}
        <div>
          <Routes>
            <Route path="/login" element={<LoginPage />} />
            <Route path="/auth" element={<AuthProcess />} />
            <Route path="/" element={<MainContainer />}></Route>
            <Route path="/mypage" element={<UserInfoPage />}></Route>
            <Route path="/map" element={<MapContainer />}></Route>
            <Route path="/point" element={<PointPageLayout />}>
              <Route path="shop" element={<PointShopContainer />} />
              <Route path="inventory" element={<InventoryContainer />} />
              <Route path="history" element={<PointHistoryContainer />} />
            </Route>
            <Route path="/vote" element={<AnswerContainer />}>
              <Route path="current" element={<AnswerPage />} />
              <Route path="past" element={<AnswerPage />} />
              {/* <Route path="detail" element={<AnswerDetailPage />} /> */}
            </Route>
            <Route path="/vote/detail/present" element={<AnswerDetailPage />} />
            <Route path="/vote/detail" element={<AnswerDetailPage />} />
            <Route path="/stat" element={<StatContainer />} />
          </Routes>
        </div>
      </div>
    </div>
  );
}

export default App;
