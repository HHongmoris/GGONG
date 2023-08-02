import './App.css';

import { BrowserRouter, Routes, Route } from 'react-router-dom';

import React from 'react';
import MenuBar from './components/MenuBar/MenuBar';

import MainContainer from './pages/Main/MainContainer';
import MapContainer from './pages/Map/MapContainer';
import PointPageLayout from './pages/Point/PointPageLayout';
import AnswerContainer from './pages/Answer/AnswerContainer';
import StatContainer from './pages/Stat/StatContainer';

import PointShopContainer from './pages/Point/PointShopContainer';
import InventoryContainer from './pages/Point/InventoryContainer';
import PointHistoryContainer from './pages/Point/PointHistoryContainer';

import AnswerPage from './pages/Answer/AnswerPage';

function App() {
  return (
    <div>
      <BrowserRouter>
        {/* 상단의 메뉴바 */}
        <MenuBar />
        {/* 경로마다 화면이 교체되는 영역 */}
        <div className="mx-5">
          <Routes>
            <Route path="/" element={<MainContainer />}></Route>
            {/* <Route path="/mypage" element={<UserInfoPage />}></Route> */}
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
      </BrowserRouter>
    </div>
  );
}

export default App;
