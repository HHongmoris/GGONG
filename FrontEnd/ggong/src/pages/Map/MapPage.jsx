import React, { useEffect, useRef } from 'react';

import useApi from '../../hooks/useApi';

// 지도 페이지
const MapPage = () => {
  const mapContainer = useRef();

  useEffect(() => {
    const mapCenter = { latitutde: '37.516615', longitude: '127.100841' };

    const mapOption = {
      center: new kakao.maps.LatLng(mapCenter.latitutde, mapCenter.longitude),
      level: 3,
    };
    const kakaoMap = new kakao.maps.Map(mapContainer.current, mapOption);

    const imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png';
    const imageSize = new kakao.maps.Size(24, 35);
    const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

    useApi('/machines', 'GET').then(res => {
      res.data.forEach(datum => {
        const machinePos = { title: datum.name, latlng: new kakao.maps.LatLng(datum.latitude, datum.longitude) };

        new kakao.maps.Marker({
          map: kakaoMap,
          position: machinePos.latlng,
          title: machinePos.title,
          image: markerImage,
        });
      });
    });
  }, []);

  return (
    <div className="mt-[-32px] z-0">
      <div id="kakaoMap" ref={mapContainer} style={{ width: '100vw', height: '100vh' }}></div>
    </div>
  );
};

export default MapPage;
