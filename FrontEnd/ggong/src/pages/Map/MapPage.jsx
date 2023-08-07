import React, { useEffect, useRef } from 'react';
import Button from '../../components/Button/Button';
import IconButton from '../../components/Button/IconButton';

// 지도 페이지
const MapPage = () => {
  const mapContainer = useRef();

  useEffect(() => {
    const mapCenter = [33.450701, 126.570667];
    const mapOption = {
      center: new kakao.maps.LatLng(mapCenter[0], mapCenter[1]),
      level: 3,
    };

    const kakaoMap = new kakao.maps.Map(mapContainer.current, mapOption);
  }, []);

  return (
    <div className="z-0">
      <div id="kakaoMap" ref={mapContainer} style={{ width: '100vw', height: '100vh' }}></div>
    </div>
  );
};

export default MapPage;
