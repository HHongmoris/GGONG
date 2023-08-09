import './App.css';
import { Routes, Route } from 'react-router-dom';
import { Background } from './global/colors';

import React from 'react';
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
import { useSelector } from 'react-redux';
import AuthPage from './pages/Login/AuthPage';

function App() {
  let user = useSelector(state => state.user);
  const bgColor = Background['GLOBAL'];

  return (
    <div className={`App min-h-screen ${bgColor}`}>
      {user.userNo === -1 ? (
        <div>
          <LoginPage />
          <Routes>
            <Route path="/auth" element={<AuthPage />}></Route>
          </Routes>
        </div>
      ) : (
        <div>
          <NavBar />
          <div className="mx-5 pb-5">
            <Routes>
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
              </Route>
              <Route path="/stat" element={<StatContainer />}></Route>
            </Routes>
          </div>
        </div>
      )}
    </div>
  );
}

export default App;
